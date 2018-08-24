package nl.bijenkorf.imageservice.controller;

import nl.bijenkorf.imageservice.exception.UnsupportedPredefinedTypeException;
import nl.bijenkorf.imageservice.model.ImageType;
import nl.bijenkorf.imageservice.model.ImageTypes;
import nl.bijenkorf.imageservice.model.PredefinedTypes;
import nl.bijenkorf.imageservice.optimization.ImageOptimizer;
import nl.bijenkorf.imageservice.storage.Storage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Optional;

@RestController
public class ImageServiceController {

    //TODO: Logger abstraction
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    Storage storage;

    @Autowired
    ImageOptimizer imageOptimizer;

    @Autowired
    HashMap<PredefinedTypes, ImageType> predefinedTypes;

    @Value("${server.default-source}")
    String defaultSource;


    //TODO: change produces to get its info from the ImageTypes enum so it's easier to add new types?
    @RequestMapping(value = {"/image/show/{predefinedType}/", "/image/show/{predefinedType}/{seo}/"}, method = RequestMethod.GET, produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public byte[] getImage(@PathVariable String predefinedType, @PathVariable Optional<String> seo, @RequestParam("reference") String relativePath) throws IOException, UnsupportedPredefinedTypeException, IllegalArgumentException
    {
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        BufferedImage returnableImage;
        ImageType imageType = predefinedTypes.get(PredefinedTypes.valueOf(predefinedType.toUpperCase()));
        if (imageType == null)
        {
            throw new UnsupportedPredefinedTypeException();
        }
        imageType.setTypeReturned(ImageTypes.valueOf(relativePath.substring(relativePath.lastIndexOf(".") + 1).toUpperCase()));
        if (storage.hasImage(imageType, relativePath))
        {
            returnableImage = storage.getImage(imageType, relativePath);
        }
        else if (storage.hasImage(predefinedTypes.get(PredefinedTypes.ORIGINAL), relativePath))
        {
            returnableImage = imageOptimizer.optimizeImage(imageType, storage.getImage(predefinedTypes.get(PredefinedTypes.ORIGINAL), relativePath));
            storage.storeImage(imageType, relativePath, returnableImage);
        }
        else
        {
            BufferedImage original = ImageIO.read(new URL(defaultSource + relativePath));
            storage.storeImage(predefinedTypes.get(PredefinedTypes.ORIGINAL),relativePath,  original);

            returnableImage = imageOptimizer.optimizeImage(imageType, original);
            storage.storeImage(imageType, relativePath, returnableImage);
        }
        ImageIO.write(returnableImage, imageType.getTypeReturned().toLowerCase(), bao);
        return bao.toByteArray();
    }

    @RequestMapping(value = "/image/flush/{predefinedType}/")
    public String flushImage(@PathVariable String predefinedType, @RequestParam("reference") String relativePath)
    {
        //TODO: Implement image removal
        return "";
    }

    @ExceptionHandler(value = {IOException.class})
    public ResponseEntity handleIOException(IOException e)
    {
        log.info("IOException caught", e);
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = {UnsupportedPredefinedTypeException.class})
    public ResponseEntity handleUnsupportedPredefinedTypeException(UnsupportedPredefinedTypeException e)
    {
        log.error("UnsupportedPredefinedTypeException caught", e);
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity handleIOException(IllegalArgumentException e)
    {
        log.error("IllegalArgumentException caught", e);
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}

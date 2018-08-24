package nl.bijenkorf.imageservice.storage;

import nl.bijenkorf.imageservice.model.ImageType;
import nl.bijenkorf.imageservice.util.PathHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LocalStorageImpl implements Storage{

    //TODO: Logger abstraction
    private Logger log = LoggerFactory.getLogger(LocalStorageImpl.class);
    private final String storageFolder = "storage";

    public LocalStorageImpl() {
        File storageDir = new File(storageFolder);
        if (!storageDir.exists() && !storageDir.mkdir()) {
            log.error("Couldn't create local storage folder");
        }
    }


    @Override
    public boolean hasImage(ImageType imageType, String filename) {
        File imageFile = new File(storageFolder + "/" + PathHelper.createRelativePath(imageType, filename));
        return imageFile.exists();
    }

    @Override
    public void storeImage(ImageType imageType, String originalName, BufferedImage buffer) throws IOException{
        String fileType = imageType.getTypeReturned().toString().toLowerCase();

        File newFile = new File(storageFolder + "/" + PathHelper.createRelativePath(imageType, originalName));
        newFile.mkdirs();
        ImageIO.write(buffer, fileType, newFile);
    }

    @Override
    public BufferedImage getImage(ImageType imageType, String filename) throws IOException {
        return ImageIO.read(new File(storageFolder + "/" + PathHelper.createRelativePath(imageType, filename)));
    }
}

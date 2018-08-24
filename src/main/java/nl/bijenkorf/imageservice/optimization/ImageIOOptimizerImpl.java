package nl.bijenkorf.imageservice.optimization;

import nl.bijenkorf.imageservice.exception.UnsupportedScaleTypeException;
import nl.bijenkorf.imageservice.model.ImageType;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageIOOptimizerImpl implements ImageOptimizer {

    @Override
    public BufferedImage optimizeImage(ImageType imageType, BufferedImage bufferedImage) throws UnsupportedScaleTypeException {
        switch (imageType.getScaleType())
        {
            case SKEW:
                return skew(bufferedImage, imageType);
            case FILL:
                return scale(bufferedImage, imageType);
            case CROP:
                return crop(bufferedImage, imageType);
            default:
                throw new UnsupportedScaleTypeException("Unsupported scale type of " + imageType.getScaleType() + " was given to the optimizer");
        }
    }

    private BufferedImage scale(BufferedImage originalImage, ImageType imageType) {

        int newWidth = originalImage.getWidth();
        int newHeight = originalImage.getHeight();

        if (originalImage.getWidth() > imageType.getWidth())
        {
            newWidth = imageType.getWidth();
            newHeight = (newWidth * originalImage.getHeight()) / originalImage.getWidth();
        }
        if (originalImage.getHeight() > imageType.getHeight())
        {
            newHeight = imageType.getHeight();
            newWidth = (newHeight * originalImage.getWidth()) / originalImage.getHeight();
        }

        int x = imageType.getWidth() / 2 - newWidth / 2;
        int y = imageType.getHeight() / 2 - newHeight / 2;

        BufferedImage scaledImage = new BufferedImage(imageType.getWidth(), imageType.getHeight(), originalImage.getType());
        Graphics2D g = scaledImage.createGraphics();
        g.drawImage(originalImage,x,y, newWidth, newHeight, null);
        g.dispose();

        return scaledImage;
    }


    private BufferedImage crop(BufferedImage image, ImageType imageType) {

        int newX = (image.getWidth() / 2) - (imageType.getWidth() / 2);
        int newY = (image.getHeight() / 2) - (imageType.getHeight() / 2);
        return image.getSubimage(newX, newY, imageType.getWidth(), imageType.getHeight());
    }

    private BufferedImage skew(BufferedImage image, ImageType imageType) {
        BufferedImage skewedImage = new BufferedImage(imageType.getWidth(), imageType.getHeight(), image.getType());
        Graphics2D g = skewedImage.createGraphics();
        g.drawImage(image, 0, 0, imageType.getWidth(), imageType.getHeight(), null);
        g.dispose();
        return skewedImage;
    }
}

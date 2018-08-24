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

    /**
     * Returns a scaled image where aspect ratio is honored and a fill will be done.
     */
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

        //TODO: use the fillColor to draw a rect first

        g.drawImage(originalImage,x,y, newWidth, newHeight, null);
        g.dispose();

        return scaledImage;
    }


    /**
     * Returns a new BufferedImage that takes the imageType sizes and crops the original image
     * IMPORTANT: currently the crop function crops on the center of the original image
     */
    private BufferedImage crop(BufferedImage image, ImageType imageType) {

        //TODO: replace this center cropping with an algorithm that tries to find the highest sharpest pixel density
        int newX = (image.getWidth() / 2) - (imageType.getWidth() / 2);
        int newY = (image.getHeight() / 2) - (imageType.getHeight() / 2);
        return image.getSubimage(newX, newY, imageType.getWidth(), imageType.getHeight());
    }

    /**
     * Returns a new BufferedImage that takes the imageType sizes and fits the original image inside it
     */
    private BufferedImage skew(BufferedImage image, ImageType imageType) {
        BufferedImage skewedImage = new BufferedImage(imageType.getWidth(), imageType.getHeight(), image.getType());
        Graphics2D g = skewedImage.createGraphics();
        g.drawImage(image, 0, 0, imageType.getWidth(), imageType.getHeight(), null);
        g.dispose();
        return skewedImage;
    }
}

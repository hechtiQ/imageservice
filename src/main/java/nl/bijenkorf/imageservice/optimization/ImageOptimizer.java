package nl.bijenkorf.imageservice.optimization;

import nl.bijenkorf.imageservice.exception.UnsupportedScaleTypeException;
import nl.bijenkorf.imageservice.model.ImageType;

import java.awt.image.BufferedImage;

public interface ImageOptimizer {

    public BufferedImage optimizeImage(ImageType imageType, BufferedImage bufferedImage) throws UnsupportedScaleTypeException;
}

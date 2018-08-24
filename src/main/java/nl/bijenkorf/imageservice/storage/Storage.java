package nl.bijenkorf.imageservice.storage;

import nl.bijenkorf.imageservice.model.ImageType;

import java.awt.image.BufferedImage;
import java.io.IOException;

public interface Storage {

    boolean hasImage(ImageType imageType, String filename);
    void storeImage(ImageType imageType, String originalName, BufferedImage buffer) throws IOException;
    BufferedImage getImage(ImageType imageType, String filename) throws IOException;
    void removeImage(ImageType imageType, String originalName) throws IOException;
}

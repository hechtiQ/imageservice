package nl.bijenkorf.imageservice.util;

import nl.bijenkorf.imageservice.model.ImageType;

import java.util.StringJoiner;

public class PathHelper {

    public static String createRelativePath(ImageType imageType, String filename) {
        filename = filename.replaceAll("/", "_");

        //Remove old file typing
        filename = filename.substring(0, filename.lastIndexOf("."));

        String suffix = "." + imageType.getTypeReturned().toLowerCase();

        StringJoiner stringJoiner = new StringJoiner("/", "", suffix);
        stringJoiner.add(imageType.getPredefinedType().toString());
        if (filename.length() > 4) {
            stringJoiner.add(filename.substring(0,4));
        }
        if (filename.length() > 8) {
            stringJoiner.add(filename.substring(4,8));
        }
        stringJoiner.add(filename);

        return stringJoiner.toString();
    }
}

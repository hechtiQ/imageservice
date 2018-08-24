package nl.bijenkorf.imageservice.model;

public class ImageType {
    private int width;
    private int height;
    private int quality;
    private ScaleTypes scaleType;
    private ImageTypes typeReturned;
    private String fillColor;
    private PredefinedTypes predefinedType;

    public ImageType(int width, int height, int quality, ScaleTypes scaleType, String fillColor, PredefinedTypes predefinedType) {
        this.width = width;
        this.height = height;
        this.quality = quality;
        this.scaleType = scaleType;
        this.fillColor = fillColor;
        this.predefinedType = predefinedType;

        //Setting a default just in case someone forgets to set it
        this.typeReturned = ImageTypes.JPG;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getQuality() {
        return quality;
    }

    public ScaleTypes getScaleType() {
        return scaleType;
    }

    public ImageTypes getTypeReturned() {
        return typeReturned;
    }

    public String getFillColor() {
        return fillColor;
    }

    public PredefinedTypes getPredefinedType() {
        return predefinedType;
    }

    public void setTypeReturned(ImageTypes typeReturned) {
        this.typeReturned = typeReturned;
    }
}

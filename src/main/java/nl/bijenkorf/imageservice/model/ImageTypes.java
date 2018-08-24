package nl.bijenkorf.imageservice.model;

public enum ImageTypes {
    JPG,
    PNG;

    public String toLowerCase() {
        return super.toString().toLowerCase();
    }
}

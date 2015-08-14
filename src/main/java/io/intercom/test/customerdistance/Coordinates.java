package io.intercom.test.customerdistance;

/**
 * @author nailgun
 * @since 12.08.15
 */
public class Coordinates {

    protected double latitude;

    protected double longitude;

    public Coordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}

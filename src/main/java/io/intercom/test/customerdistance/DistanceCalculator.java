package io.intercom.test.customerdistance;

/**
 * @author nailgun
 * @since 12.08.15
 */
public class DistanceCalculator {

    public int distanceKm(Coordinates from, Coordinates to) {
        if (from == null || to == null) {
            throw new NullPointerException();
        }
        double theta = from.getLongitude() - to.getLongitude();
        double dist = Math.sin(deg2rad(from.getLatitude())) * Math.sin(deg2rad(to.getLatitude())) +
                Math.cos(deg2rad(from.getLatitude())) * Math.cos(deg2rad(to.getLatitude())) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.853159616;
        return (int) Math.round(dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }


}

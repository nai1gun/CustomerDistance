package io.intercom.test.customerdistance;

/**
 *
 * Customer bean
 *
 * @author nailgun
 * @since 11.08.15
 */
public class Customer extends Coordinates {

    private int userId;

    private String name;

    public Customer(double latitude, double longitude, int userId, String name) {
        super(latitude, longitude);
        this.userId = userId;
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }
}

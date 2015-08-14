package io.intercom.test.customerdistance;

import java.util.List;

/**
 *
 * Prints customers to command line
 *
 * @author nailgun
 * @since 13.08.15
 */
public class CustomerPrinter {

    private String heading;

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public void printHeading() {
        System.out.println(heading);
    }

    public void print(List<Customer> customers) {
        if (customers != null) {
            for (Customer customer: customers) {
                System.out.format("id: %s, name: '%s'\n", customer.getUserId(), customer.getName());
            }
        }
    }

}

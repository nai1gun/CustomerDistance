package io.intercom.test.customerdistance;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author nailgun
 * @since 13.08.15
 */
public class CustomerFilter {

    private DistanceCalculator distanceCalculator;

    public void setDistanceCalculator(DistanceCalculator distanceCalculator) {
        this.distanceCalculator = distanceCalculator;
    }

    public List<Customer> filterDistanceFromPoint(List<Customer> customers, Coordinates point, int distanceKm) {
        if (customers == null || point == null || distanceKm < 0) {
            throw new IllegalArgumentException();
        }
        return customers.stream()
                .filter(customer -> distanceCalculator.distanceKm(customer, point) < distanceKm)
                .collect(Collectors.toList());
    }

}

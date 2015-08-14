package io.intercom.test.customerdistance;

import junit.framework.Assert;
import org.junit.Test;

/**
 * @author nailgun
 * @since 12.08.15
 */
public class DistanceCalculatorTest {

    private DistanceCalculator distanceCalculator = new DistanceCalculator();

    @Test
    public void testMskSpbDistance() {
        Coordinates msk = new Coordinates(55.7522, 37.6156);
        Coordinates spb = new Coordinates(59.8944, 30.2642);
        int distanceKm = distanceCalculator.distanceKm(msk, spb);
        Assert.assertEquals(633, distanceKm);
    }

    @Test
    public void testDublinLondonDistance() {
        Coordinates dublin = new Coordinates(53.3331, -6.2489);
        Coordinates london = new Coordinates(51.5000, -0.1167);
        int distanceKm = distanceCalculator.distanceKm(dublin, london);
        Assert.assertEquals(463, distanceKm);
    }

    @Test(expected = NullPointerException.class)
    public void testDublinNullDistance() {
        Coordinates dublin = new Coordinates(53.3331, -6.2489);
        Coordinates nil = null;
        distanceCalculator.distanceKm(dublin, nil);
    }

}

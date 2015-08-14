package io.intercom.test.customerdistance;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author nailgun
 * @since 12.08.15
 */
public class CustomerParserTest {

    private static final double DELTA = 0.0000001;

    CustomerParser customerParser = new CustomerParser();

    @Test
    public void normalParseTest() {
        String entry = "{\"latitude\": \"53.1229599\", \"user_id\": 6, \"name\": \"Theresa Enright\", \"longitude\": \"-6.2705202\"}";
        Customer parsed = null;
        try {
            parsed = customerParser.parseLine(entry);
        } catch (CustomerParser.ParseException e) {
            Assert.assertTrue(e.getMessage(), false);
        }
        Assert.assertNotNull(parsed);
        Assert.assertEquals("Theresa Enright", parsed.getName());
        Assert.assertEquals(6, parsed.getUserId());
        Assert.assertEquals(53.1229599, parsed.getLatitude(), DELTA);
        Assert.assertEquals(-6.2705202, parsed.getLongitude(), DELTA);
    }

    @Test
    public void normalParseTestShuffle() {
        String entry = "{\"name\": \"Theresa Enright\", \"user_id\": 6, \"latitude\": \"53.1229599\", \"longitude\": \"-6.2705202\"}";
        Customer parsed = null;
        try {
            parsed = customerParser.parseLine(entry);
        } catch (CustomerParser.ParseException e) {
            Assert.assertTrue(e.getMessage(), false);
        }
        Assert.assertNotNull(parsed);
        Assert.assertEquals("Theresa Enright", parsed.getName());
        Assert.assertEquals(6, parsed.getUserId());
        Assert.assertEquals(53.1229599, parsed.getLatitude(), DELTA);
        Assert.assertEquals(-6.2705202, parsed.getLongitude(), DELTA);
    }

    @Test
    public void normalParseTestIgnoreAdditionalAttributes() {
        String entry = "{\"name\": \"Theresa Enright\", \"user_id\": 6, \"latitude\": \"53.1229599\", \"longitude\": \"-6.2705202\"" +
                ", \"new_attribute\": \"value\"}";
        Customer parsed = null;
        try {
            parsed = customerParser.parseLine(entry);
        } catch (CustomerParser.ParseException e) {
            Assert.assertTrue(e.getMessage(), false);
        }
        Assert.assertNotNull(parsed);
        Assert.assertEquals("Theresa Enright", parsed.getName());
        Assert.assertEquals(6, parsed.getUserId());
        Assert.assertEquals(53.1229599, parsed.getLatitude(), DELTA);
        Assert.assertEquals(-6.2705202, parsed.getLongitude(), DELTA);
    }

    @Test(expected = CustomerParser.ParseException.class)
    public void parseTestNull() throws CustomerParser.ParseException {
        String entry = null;
        customerParser.parseLine(entry);
    }

    @Test(expected = CustomerParser.ParseException.class)
    public void parseTestError() throws CustomerParser.ParseException {
        String entry = "{test}";
        customerParser.parseLine(entry);
    }

    @Test(expected = CustomerParser.ParseException.class)
    public void parseTestNoName() throws CustomerParser.ParseException {
        String entry = "{\"user_id\": 6, \"latitude\": \"53.1229599\", \"longitude\": \"-6.2705202\"}";
        customerParser.parseLine(entry);
    }

    @Test(expected = CustomerParser.ParseException.class)
    public void parseTestWrongName() throws CustomerParser.ParseException {
        String entry = "{\"wrong_name\": \"Theresa Enright\", \"user_id\": 6, \"latitude\": \"53.1229599\", \"longitude\": \"-6.2705202\"}";
        customerParser.parseLine(entry);
    }

    @Test(expected = CustomerParser.ParseException.class)
    public void parseTestWrongValues1() throws CustomerParser.ParseException {
        String entry = "{\"name\": \"Theresa Enright\", \"user_id\": \"test\", \"latitude\": \"53.1229599\", \"longitude\": \"-6.2705202\"}";
        customerParser.parseLine(entry);
    }

    @Test(expected = CustomerParser.ParseException.class)
    public void parseTestWrongValues2() throws CustomerParser.ParseException {
        String entry = "{\"name\": \"Theresa Enright\", \"user_id\": 6, \"latitude\": \"test\", \"longitude\": \"-6.2705202\"}";
        customerParser.parseLine(entry);
    }

    @Test(expected = CustomerParser.ParseException.class)
    public void parseTestWrongValues3() throws CustomerParser.ParseException {
        String entry = "{\"name\": 123, \"user_id\": 6, \"latitude\": \"53.1229599\", \"longitude\": \"-6.2705202\"}";
        customerParser.parseLine(entry);
    }

}

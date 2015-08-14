package io.intercom.test.customerdistance;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author nailgun
 * @since 11.08.15
 */
public class CustomerParser {

    private static final Pattern JSON_PATTERN = Pattern.compile(".*\"(.*)\":\\s*((\\d+)|\"(.*)\").*");

    private static final String JSON_NAME_ATTR = "name";
    private static final String JSON_LAT_ATTR = "latitude";
    private static final String JSON_LNG_ATTR = "longitude";
    private static final String JSON_ID_ATTR = "user_id";

    public List<Customer> parse(List<String> input) {
        if (input == null) {
            return null;
        }
        List<Customer> result = new ArrayList<>(input.size());
        for (String inputItem: input) {
            Customer customer;
            try {
                customer = parseLine(inputItem);
            } catch (ParseException e) {
                e.printStackTrace();
                continue;
            }
            result.add(customer);
        }
        return result;
    }

    public Customer parseLine(String inputItem) throws ParseException {
        if (inputItem != null) {
            String[] attributes = inputItem.split(",");
            if (attributes.length >= 4) {
                Double latitude = null, longitude = null;
                String name = null;
                Integer id = null;
                for (String attributeRaw: attributes) {
                    Matcher matcher = JSON_PATTERN.matcher(attributeRaw);
                    if (matcher.matches()) {
                        String attrName = matcher.group(1);
                        String attrValueStr = matcher.group(4);
                        Integer attrValueInt = matcher.group(3) != null ? Integer.parseInt(matcher.group(3)) : null;
                        if (attrName != null && !attrName.isEmpty()) {
                            switch (attrName) {
                                case JSON_NAME_ATTR:
                                case JSON_LAT_ATTR:
                                case JSON_LNG_ATTR:
                                    if (attrValueStr == null) {
                                        throw new ParseException(String.format(
                                                "No attribute value for '%s': '%s'.", attrName, attributeRaw));
                                    }
                                    break;
                                case JSON_ID_ATTR:
                                    if (attrValueInt == null) {
                                        throw new ParseException(String.format(
                                                "No attribute value for 'id': '%s'.", attributeRaw));
                                    }
                            }
                            try {
                                switch (attrName) {
                                    case JSON_NAME_ATTR:
                                        name = attrValueStr;
                                        break;
                                    case JSON_LAT_ATTR:
                                        latitude = Double.parseDouble(attrValueStr);
                                        break;
                                    case JSON_LNG_ATTR:
                                        longitude = Double.parseDouble(attrValueStr);
                                        break;
                                    case JSON_ID_ATTR:
                                        id = attrValueInt;

                                }
                            } catch (NumberFormatException e) {
                                throw new ParseException(
                                    String.format("Couldn't parse attribute: '%s'.", attributeRaw), e);
                            }
                        } else {
                            throw new ParseException(String.format(
                                    "No attribute name: '%s'.", attributeRaw));
                        }
                    } else {
                        throw new ParseException(String.format("Input '%s' doesn't match.", attributeRaw));
                    }
                }
                if (latitude == null || longitude == null || name == null || id == null) {
                    throw new ParseException("Not enough attributes in entry: '%s'");
                }
                return new Customer(latitude, longitude, id, name);
            } else {
                throw new ParseException(String.format(
                        "The number of attributes in customer item should be not less than 4 - '%s'", inputItem));
            }
        } else {
            throw new ParseException("Null item.");
        }
    }

    public static class ParseException extends Exception {

        private ParseException(String message) {
            super(message);
        }

        private ParseException(String message, Throwable cause) {
            super(message, cause);
        }
    }

}

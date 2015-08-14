package io.intercom.test.customerdistance;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

/**
 * @author nailgun
 * @since 13.08.15
 */
public class CustomerRepositoryTest {

    @Test
    public void sortingTest() {
        FileToListReader readerMock = new FileToListReader() {
            public List<String> read(String fileName) throws FileNotFoundException {
                return Arrays.asList("{\"latitude\": \"52.986375\", \"user_id\": 12, \"name\": \"Christina McArdle\", \"longitude\": \"-6.043701\"}",
                        "{\"latitude\": \"51.92893\", \"user_id\": 1, \"name\": \"Alice Cahill\", \"longitude\": \"-10.27699\"}",
                        "{\"latitude\": \"51.8856167\", \"user_id\": 2, \"name\": \"Ian McArdle\", \"longitude\": \"-10.4240951\"}");
            }
        };
        CustomerParser parser = new CustomerParser();
        CustomerRepository customerRepository = new CustomerRepository();
        customerRepository.setCustomerParser(parser);
        customerRepository.setFileToListReader(readerMock);
        customerRepository.setFileName("test");
        List<Customer> sortedCustomers = customerRepository.getAllCustomersSortedById();
        Assert.assertNotNull(sortedCustomers);
        Assert.assertEquals(3, sortedCustomers.size());
        Assert.assertEquals(1, sortedCustomers.get(0).getUserId());
        Assert.assertEquals(2, sortedCustomers.get(1).getUserId());
        Assert.assertEquals(12, sortedCustomers.get(2).getUserId());
    }

}

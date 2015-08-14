package io.intercom.test.customerdistance;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;

/**
 *
 * The source of customers
 *
 * @author nailgun
 * @since 13.08.15
 */
public class CustomerRepository {

    private String fileName;

    private FileToListReader fileToListReader;

    private CustomerParser customerParser;

    public void setFileToListReader(FileToListReader fileToListReader) {
        this.fileToListReader = fileToListReader;
    }

    public void setCustomerParser(CustomerParser customerParser) {
        this.customerParser = customerParser;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<Customer> getAllCustomersSortedById() {
        List<String> lines;
        try {
            lines = fileToListReader.read(fileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        List<Customer> customers = customerParser.parse(lines);
        Collections.sort(customers, (customer1, customer2) -> customer1.getUserId() - customer2.getUserId());
        return customers;
    }
}

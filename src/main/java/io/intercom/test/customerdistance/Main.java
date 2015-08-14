package io.intercom.test.customerdistance;

import java.util.List;

/**
 * @author nailgun
 * @since 13.08.15
 */
public class Main {

    private CustomerRepository customerRepository;

    private CustomerFilter customerFilter;

    private CustomerPrinter customerPrinter;

    public static void main(String[] args) {
        Main main = new Main();
        main.printCustomers100KmFromDublin();
    }

    public void printCustomers100KmFromDublin() {
        Coordinates dublin = new Coordinates(53.3381985, -6.2592576);
        List<Customer> allCustomers = customerRepository.getAllCustomersSortedById();
        List<Customer> filtered = customerFilter.filterDistanceFromPoint(allCustomers, dublin, 100);
        customerPrinter.printHeading();
        customerPrinter.print(filtered);
    }

    public Main() {
        customerRepository = new CustomerRepository();
        customerRepository.setFileName("customers.txt");
        customerRepository.setCustomerParser(new CustomerParser());
        customerRepository.setFileToListReader(new FileToListReader());
        customerFilter = new CustomerFilter();
        customerFilter.setDistanceCalculator(new DistanceCalculator());
        customerPrinter = new CustomerPrinter();
        customerPrinter.setHeading("Customers within 100 km from Dublin.");
    }
}

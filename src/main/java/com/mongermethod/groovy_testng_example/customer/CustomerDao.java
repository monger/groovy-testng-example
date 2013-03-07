package com.mongermethod.groovy_testng_example.customer;

import java.util.List;

public interface CustomerDao {
    List<Customer> getAllCustomers();
    void saveCustomer(Customer customer);
    Customer loadCustomer(String username);
    void deleteCustomer(Customer customer);
}

package com.nagarro.service;

import com.nagarro.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> findAllCustomers();
    Customer saveCustomer(Customer customer);
    Optional<Customer> findCustomer(Long customerId);

    Optional<Customer> findCustomerByAccNo(String accNo);

    void deleteCustomer(Long customerId);
}

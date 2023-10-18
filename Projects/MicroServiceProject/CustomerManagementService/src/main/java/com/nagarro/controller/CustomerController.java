package com.nagarro.controller;

import com.nagarro.constants.Constant;
import com.nagarro.model.Customer;
import com.nagarro.response.ResponseHandler;
import com.nagarro.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/banking/customer/all")
    public ResponseEntity<Object> getAllCustomers() {
        try {
            List<Customer> customers = customerService.findAllCustomers();
            if (customers.isEmpty() || customers.size() == 0) {
                return ResponseHandler.generateResponse(Constant.NO_DATA_MESSAGE, HttpStatus.NO_CONTENT, null);
            }
            return ResponseHandler.generateResponse(Constant.SUCCESS_GET_MESSAGE, HttpStatus.OK, customers);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/banking/customer/{customerId}")
    public ResponseEntity<Object> getCustomer(@PathVariable Long customerId) {
        try {
            Optional<Customer> searchedCustomer = customerService.findCustomer(customerId);
            if (searchedCustomer.isEmpty()) {
                return ResponseHandler.generateResponse(Constant.NO_DATA_MESSAGE, HttpStatus.NO_CONTENT, null);
            }
            return ResponseHandler.generateResponse(Constant.SUCCESS_GET_MESSAGE, HttpStatus.OK, searchedCustomer.get());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
    @GetMapping("/banking/customerByAccountNo/{accountNo}")
    public Customer getCustomerByAccountNo(@PathVariable String accountNo) {
        try {
            Optional<Customer> searchedCustomer = customerService.findCustomerByAccNo(accountNo);
            if (searchedCustomer.isEmpty()) {
                return null;
            }
            return searchedCustomer.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @PostMapping("/banking/customer")
    public ResponseEntity<Object> addCustomer(@RequestBody Customer customer) {
        try {
                Customer savedCustomer = customerService.saveCustomer(customer);
                if (savedCustomer == null) {
                    return ResponseHandler.generateResponse(Constant.CONFLICT_MESSAGE, HttpStatus.CONFLICT,
                            savedCustomer);
                }
            String createAccountUrl = Constant.accountManagementServiceUrl+"/banking/accounts/save/"+savedCustomer.getAccno();
            restTemplate.postForObject(createAccountUrl,null,String.class);
            return ResponseHandler.generateResponse(Constant.SUCCESS_ADD_MESSAGE, HttpStatus.CREATED,
                    savedCustomer);
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
            return ResponseHandler.generateResponse(Constant.CONFLICT_MESSAGE, HttpStatus.CONFLICT, null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PutMapping("/banking/customer/{customerId}")
    public ResponseEntity<Object> updateCustomer(@RequestBody Customer customer, @PathVariable Long customerId) {
        try {
            Optional<Customer> existCustomer = customerService.findCustomer(customerId);
            if (existCustomer.isPresent()) {
                Customer searchedCustomer = existCustomer.get();
                searchedCustomer.setAccno(customer.getAccno());
                searchedCustomer.setName(customer.getName());
                Customer updatedCustomer = customerService.saveCustomer(searchedCustomer);
                return ResponseHandler.generateResponse(Constant.UPDATE_SUCCESS_MESSAGE, HttpStatus.OK, updatedCustomer);
            }
            return ResponseHandler.generateResponse(Constant.CONFLICT_MESSAGE_CUSTOMER_NOT_PRESENT, HttpStatus.CONFLICT,
                    null);
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
            return ResponseHandler.generateResponse(Constant.CONFLICT_MESSAGE, HttpStatus.CONFLICT, null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @DeleteMapping("/banking/customer/{customerId}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable Long customerId) {
        try {
            Optional<Customer> deletedCustomer = customerService.findCustomer(customerId);
            if (deletedCustomer.isPresent()) {
                customerService.deleteCustomer(customerId);
                return ResponseHandler.generateResponse(Constant.DELETE_SUCCESS_MESSAGE, HttpStatus.OK, deletedCustomer);
            }
            return ResponseHandler.generateResponse(Constant.NO_DATA_MESSAGE, HttpStatus.CONFLICT,null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

}

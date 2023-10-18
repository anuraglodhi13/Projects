package com.nagarro.controller;

import com.nagarro.constants.Constant;
import com.nagarro.model.Account;
import com.nagarro.model.Customer;
import com.nagarro.model.CustomerAccount;
import com.nagarro.response.ResponseHandler;
import com.nagarro.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Optional;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/banking/account/{accountNo}")
    public ResponseEntity<Object> getAccount(@PathVariable String accountNo) {
        try {
            Optional<Account> existAccount = accountService.findAccount(Long.parseLong(accountNo));
            if (existAccount.isPresent()) {
                Account searchedAccount = existAccount.get();
                String getCustomerByAccNoUrl = Constant.customerManagementServiceUrl+"/banking/customerByAccountNo/"+accountNo;
                Customer customer = restTemplate.getForObject(getCustomerByAccNoUrl, Customer.class);
                CustomerAccount customerAccount = new CustomerAccount();
                customerAccount.setAccno(searchedAccount.getAccno());
                customerAccount.setMoney(searchedAccount.getMoney());
                customerAccount.setCustomerId(customer.getCustomerId());
                customerAccount.setName(customer.getName());
                customerAccount.setEmail(customer.getEmail());
                return ResponseHandler.generateResponse(Constant.SUCCESS_GET_MESSAGE, HttpStatus.OK, customerAccount);
            }
            return ResponseHandler.generateResponse(Constant.NO_DATA_MESSAGE, HttpStatus.NO_CONTENT, null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PostMapping("/banking/accounts/save/{accountNo}")
    public ResponseEntity<Object> addAccount(@PathVariable String accountNo) {
        try {
            Account account = new Account();
            account.setMoney("0");
            account.setAccno(accountNo);
                Account savedAccount = accountService.saveAccount(account);
                if (savedAccount == null) {
                    return ResponseHandler.generateResponse(Constant.CONFLICT_MESSAGE, HttpStatus.CONFLICT,
                            savedAccount);
                }
            return ResponseHandler.generateResponse(Constant.SUCCESS_ADD_MESSAGE, HttpStatus.CREATED,
                    savedAccount);
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
            return ResponseHandler.generateResponse(Constant.CONFLICT_MESSAGE, HttpStatus.CONFLICT, null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PutMapping("/banking/account/addmoney")
    public ResponseEntity<Object> addMoney(@RequestBody Account account) {
        try {
            Optional<Account> existAccount = accountService.findAccount(Long.parseLong(account.getAccno()));
            if (existAccount.isPresent()) {
                Account searchedAccount = existAccount.get();
                Long finalMoney = Long.parseLong(searchedAccount.getMoney()) + Long.parseLong(account.getMoney());
                searchedAccount.setMoney(finalMoney.toString());
                Account updatedAccount = accountService.saveAccount(searchedAccount);
                return ResponseHandler.generateResponse(Constant.UPDATE_SUCCESS_MESSAGE, HttpStatus.OK, updatedAccount);
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

    @PutMapping("/banking/account/withdrawmoney")
    public ResponseEntity<Object> withdrawMoney(@RequestBody Account account) {
        try {
            Optional<Account> existAccount = accountService.findAccount(Long.parseLong(account.getAccno()));
            if (existAccount.isPresent()) {
                Account searchedAccount = existAccount.get();
                if(Long.parseLong(searchedAccount.getMoney()) < Long.parseLong(account.getMoney())) {
                    return ResponseHandler.generateResponse("Insufficient Balance", HttpStatus.CONFLICT, null);
                }
                Long finalMoney = Long.parseLong(searchedAccount.getMoney()) - Long.parseLong(account.getMoney());
                searchedAccount.setMoney(finalMoney.toString());
                Account updatedAccount = accountService.saveAccount(searchedAccount);
                return ResponseHandler.generateResponse(Constant.UPDATE_SUCCESS_MESSAGE, HttpStatus.OK, updatedAccount);
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


    @DeleteMapping("/banking/account/{accountNo}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable Long accountNo) {
        try {
            Optional<Account> deletedCustomer = accountService.findAccount(accountNo);
            if (deletedCustomer.isPresent()) {
                accountService.deleteAccount(deletedCustomer.get().getId());
                String getCustomerByAccNoUrl = Constant.customerManagementServiceUrl+"/banking/customerByAccountNo/"+accountNo;
                Customer customer = restTemplate.getForObject(getCustomerByAccNoUrl, Customer.class);
                String deleteCustomerUrl = Constant.customerManagementServiceUrl+"/banking/customer/"+customer.getCustomerId();
                restTemplate.delete(deleteCustomerUrl);
                return ResponseHandler.generateResponse(Constant.DELETE_SUCCESS_MESSAGE, HttpStatus.OK, deletedCustomer);
            }
            return ResponseHandler.generateResponse(Constant.NO_DATA_MESSAGE, HttpStatus.CONFLICT,null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

}

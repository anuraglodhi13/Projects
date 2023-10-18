package com.nagarro.failureapplication.controller;

import com.nagarro.failureapplication.dto.ResponseDto;
import com.nagarro.failureapplication.model.Transaction;
import com.nagarro.failureapplication.repository.AccountRepository;
import com.nagarro.failureapplication.response.ResponseHandler;
import com.nagarro.failureapplication.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @Autowired
    AccountRepository accountRepository;
    @GetMapping("backendserver3/failure/{accountNumber}")
    public ResponseEntity<Object> geTransactions(@PathVariable String accountNumber) {
        try {
            String status = "failure";
            List<Transaction> transactionsList = transactionService.findByAccountNumberAndStatus(accountNumber, status);
            ResponseDto responseDto = new ResponseDto();
            responseDto.setAccountNumber(accountNumber);
            responseDto.setFailure(transactionsList);
            if(transactionsList.size() != 0) {

                return ResponseHandler.generateResponse(null, HttpStatus.OK, responseDto);
            }
            return ResponseHandler.generateResponse("No Data Found", HttpStatus.NOT_FOUND, null);
        }
        catch(Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateResponse(null, HttpStatus.NOT_FOUND, null);
        }
    }

}

package com.nagarro.pendingapplication.controller;

import com.nagarro.pendingapplication.dto.ResponseDto;
import com.nagarro.pendingapplication.model.Transaction;
import com.nagarro.pendingapplication.repository.AccountRepository;
import com.nagarro.pendingapplication.response.ResponseHandler;
import com.nagarro.pendingapplication.service.TransactionService;
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
    @GetMapping("backendserver1/pending/{accountNumber}")
    public ResponseEntity<Object> geTransactions(@PathVariable String accountNumber) {
        try {
            String status = "pending";
            List<Transaction> transactionList = transactionService.findByAccountNumberAndStatus(accountNumber, status);
            ResponseDto responseDto = new ResponseDto();
            responseDto.setAccountNumber(accountNumber);
            responseDto.setPending(transactionList);
            if(transactionList.size() != 0) {

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

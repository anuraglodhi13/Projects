package com.nagarro.mainapplication.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.nagarro.mainapplication.response.ResponseHandler;
import com.nagarro.mainapplication.utils.AllTransactionUtil;
import com.nagarro.mainapplication.utils.FailureTransactionUtil;
import com.nagarro.mainapplication.utils.PendingTransactionUtil;
import com.nagarro.mainapplication.utils.SuccessTransactionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
public class TransactionController {
    @Autowired
    SuccessTransactionUtil successTransactionUtil;

    @Autowired
    AllTransactionUtil allTransactionUtil;

    @Autowired
    PendingTransactionUtil pendingTransactionUtil;
    @Autowired
    FailureTransactionUtil failureTransactionUtil;

    @GetMapping("/transactions/{accountNumber}")
    public ResponseEntity<Object> getTransactions(@PathVariable String accountNumber,
    @RequestParam(value = "status") String status) {
        try {
            if(Objects.equals(status, "pending")) {
                JsonNode pendingData = pendingTransactionUtil.getPending(accountNumber);
                return ResponseHandler.generateResponse(null, HttpStatus.OK, pendingData);
            }
            else if(Objects.equals(status, "failure")) {
                JsonNode failuredData = failureTransactionUtil.getFailure(accountNumber);
                return ResponseHandler.generateResponse(null, HttpStatus.OK, failuredData);

            }
            else if(Objects.equals(status, "success")) {
                JsonNode successData = successTransactionUtil.getSuccess(accountNumber);
                return ResponseHandler.generateResponse(null, HttpStatus.OK, successData);
            }
            else {
                List<Object> allTransactions = allTransactionUtil.getAll(accountNumber,status);
                return ResponseHandler.generateResponse(null, HttpStatus.OK, allTransactions);

            }
        }
        catch(Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateResponse(null, HttpStatus.NOT_FOUND, null);
        }
    }

}

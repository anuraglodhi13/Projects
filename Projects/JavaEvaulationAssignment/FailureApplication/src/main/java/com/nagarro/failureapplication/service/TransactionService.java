package com.nagarro.failureapplication.service;

import com.nagarro.failureapplication.model.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> findByAccountNumberAndStatus(String accountNumber, String status);
}

package com.nagarro.successapplication.service;

import com.nagarro.successapplication.model.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> findByAccountNumberAndStatus(String accountNumber, String status);
}

package com.nagarro.pendingapplication.service;

import com.nagarro.pendingapplication.model.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> findByAccountNumberAndStatus(String accountNumber, String status);
}

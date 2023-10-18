package com.nagarro.mainapplication.service;

import com.nagarro.mainapplication.model.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> findByAccountNumberAndStatus(String accountNumber, String status);
}

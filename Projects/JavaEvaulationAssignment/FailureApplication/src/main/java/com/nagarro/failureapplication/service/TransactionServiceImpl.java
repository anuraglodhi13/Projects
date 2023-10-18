package com.nagarro.failureapplication.service;

import com.nagarro.failureapplication.model.Transaction;
import com.nagarro.failureapplication.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public List<Transaction> findByAccountNumberAndStatus(String accountNumber, String status) {
        return transactionRepository.findFailureData(accountNumber,status);
    }
}

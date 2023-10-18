package com.nagarro.pendingapplication.service;

import com.nagarro.pendingapplication.model.Transaction;
import com.nagarro.pendingapplication.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public List<Transaction> findByAccountNumberAndStatus(String accountNumber, String status) {
        return transactionRepository.findPendingData(accountNumber,status);
    }
}

package com.nagarro.successapplication.service;

import com.nagarro.successapplication.model.Transaction;
import com.nagarro.successapplication.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public List<Transaction> findByAccountNumberAndStatus(String accountNumber, String status) {
        return transactionRepository.findSuccessData(accountNumber,status);
    }
}

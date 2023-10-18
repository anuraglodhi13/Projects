package com.nagarro.failureapplication.repository;

import com.nagarro.failureapplication.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {

    @Query(value = "SELECT t.*, t.account_number " +
            "FROM transaction t " +
            "WHERE t.account_number = :accountNumber AND t.status = :status" , nativeQuery = true)
    List<Transaction> findFailureData(String accountNumber, String status);
}

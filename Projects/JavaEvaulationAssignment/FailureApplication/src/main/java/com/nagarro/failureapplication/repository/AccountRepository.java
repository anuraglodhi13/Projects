package com.nagarro.failureapplication.repository;

import com.nagarro.failureapplication.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    Account findByAccountNumber(String accountNumber);
}

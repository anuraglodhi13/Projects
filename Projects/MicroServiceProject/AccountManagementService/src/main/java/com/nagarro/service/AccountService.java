package com.nagarro.service;

import com.nagarro.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    List<Account> findAllAccounts();
    Account saveAccount(Account account);
    Optional<Account> findAccount(Long accountId);
    void deleteAccount(Long accountId);
}

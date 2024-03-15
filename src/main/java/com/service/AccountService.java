package com.service;


import com.dto.AccountDTO;
import com.entity.Account;

import java.util.List;

public interface AccountService {
    List<AccountDTO> getAllAccount();

    AccountDTO getAccountById(int id);

    List<AccountDTO> getAccountByCustomerId(int id);

    AccountDTO addAccount(Account account) throws Exception;

    AccountDTO updateAccount(Account account, int accountId) throws Exception;

    AccountDTO deleteAccountById(int id);
}

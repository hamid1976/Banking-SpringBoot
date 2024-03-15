package com.transformer;

import com.dto.AccountDTO;
import com.entity.Account;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class AccountTransformer {
    public static AccountDTO toDto(Account entity) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(entity.getId());
        accountDTO.setAccNo(entity.getAccNo());
        accountDTO.setAccType(entity.getAccType());
        accountDTO.setPinCode(entity.getPinCode());
        accountDTO.setDateOfCreate(entity.getDateOfCreate());
        accountDTO.setAmount(entity.getAmount());
        accountDTO.setRemarks(entity.getRemarks());

        // Set CustomerDTO if customer is not null
        if (entity.getCustomer() != null) {
            accountDTO.setCustomerId(entity.getCustomer().getId());
        }

        return accountDTO;
    }

    public static List<AccountDTO> toDTO(List<Account> accounts) {
        List<AccountDTO> accountDTOs = new ArrayList<>();
        if (!CollectionUtils.isEmpty(accounts)) {
            for (Account account : accounts) {
                accountDTOs.add(toDto(account));
            }
        }
        return accountDTOs;
    }
}

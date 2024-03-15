package com.controller;

import com.dto.AccountDTO;
import com.dto.CustomerDTO;
import com.entity.Account;
import com.entity.Customer;
import com.exception.ResponseUtils;
import com.service.AccountService;
import com.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path="/account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService customerService) {
        this.accountService = customerService;
    }

    @GetMapping(path = "/getAll")
    public Object getAllAccount() {
        try {
            List<AccountDTO> list = accountService.getAllAccount();
            if (list == null || list.isEmpty()) {
                return ResponseUtils.returnResponseForNull("Success");
            }
            return ResponseUtils.returnResponseWithCustomMessage(HttpStatus.OK.value(), "Success", list);
        } catch (Exception e) {
            log.error("Error occurred while fetching customers: {}", e.getMessage());
            return ResponseUtils.returnResponse(e);
        }
    }//end getAllAccount
    @GetMapping(path = "/getById/{id}")
    public Object getAccountById(@PathVariable int id) {
        try {
            AccountDTO dto = accountService.getAccountById(id);
            if (dto == null) {
                return ResponseUtils.returnResponseForNull("Success");
            }
            return ResponseUtils.returnResponseWithCustomMessage(HttpStatus.OK.value(), "Success", dto);
        } catch (Exception e) {
            log.error("Error occurred while fetching customers: {}", e.getMessage());
            return ResponseUtils.returnResponse(e);
        }
    }//end getAccountById
    @GetMapping(path = "/getAccountByCustomerId/{id}")
    public Object getAccountByCustomerId(@PathVariable int id) {
        try {
            List<AccountDTO> list = accountService.getAccountByCustomerId(id);
            if (list == null || list.isEmpty()) {
                return ResponseUtils.returnResponseForNull("Success");
            }
            return ResponseUtils.returnResponseWithCustomMessage(HttpStatus.OK.value(), "Success", list);
        } catch (Exception e) {
            log.error("Error occurred while fetching customers: {}", e.getMessage());
            return ResponseUtils.returnResponse(e);
        }
    }//end getAccountByCustomerId
    @PostMapping(path = "/add")
    public Object addAccount(@RequestBody Account account) {
        try {
            AccountDTO accountDTO = accountService.addAccount(account);
            if (accountDTO == null) {
                return ResponseUtils.customerNotFound(account);
            }
            return ResponseUtils.returnResponseForAccount(accountDTO);
        } catch (Exception e) {
            log.error("Error occurred while Adding customers: {}", e.getMessage());
            return ResponseUtils.returnResponse(e);
        }
    }//end add record
    @PostMapping(path="/update/{id}")
    public Object updateAccount (@RequestBody Account account, @PathVariable("id")int accountId){
        try {
            AccountDTO dto1 = accountService.updateAccount(account,accountId);
            System.out.println(dto1);
            if (dto1==null) {
                return ResponseUtils.customerNotFound(account);
            }
            return ResponseUtils.updateResponseForAccount(dto1);
        } catch (Exception e) {
            log.error("Error occurred while updating customer: {}", e.getMessage());
            return ResponseUtils.returnResponse(e);
        }
    }//end update record
    @PostMapping(path="/delete/{id}")
    public Object deleteAccountById(@PathVariable int id) {
        try {
            AccountDTO dto = accountService.deleteAccountById(id);
            System.out.println(dto);
            if (dto==null) {
                return ResponseUtils.returnResponseForNull("Success");
            }
            return ResponseUtils.deleteResponseForAccount(dto);
        } catch (Exception e) {
            log.error("Error occurred while fetching customers: {}", e.getMessage());
            return ResponseUtils.returnResponse(e);
        }
    }//end delete record
}


package com.serviceImpl;


import com.dto.AccountDTO;
import com.dto.CustomerDTO;
import com.entity.Account;
import com.entity.Customer;
import com.exception.CustomerNotFoundException;
import com.repository.AccountRepository;
import com.repository.CustomerRepository;
import com.service.AccountService;
import com.transformer.AccountTransformer;
import com.transformer.CustomerTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Slf4j
@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    private final CustomerRepository customerRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository,CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository=customerRepository;
    }
    @Override
    public List<AccountDTO> getAllAccount() {
        List<Account> accounts = accountRepository.findAll();
        return AccountTransformer.toDTO(accounts);
    }//getAllAccount

    @Override
    public AccountDTO getAccountById(int id) {
        Account account = accountRepository.findById(id);
        if (account != null) {
            return AccountTransformer.toDto(account);
        }
        return null;
    }//GetCustomerById

    @Override
    public List<AccountDTO> getAccountByCustomerId(int id) {
        List<Account> accounts = accountRepository.findByCustomer_Id(id);
        return AccountTransformer.toDTO(accounts);

    }

   /* @Override
    public AccountDTO addAccount(Account account)throws Exception{
        Customer existingCustomer = customerRepository.findById(account.getCustomer().getId());
        if (existingCustomer == null) {
            throw new Exception("Customer with ID " + account.getCustomer().getId() + " not found.");
        }
        Account savedAccount = accountRepository.save(account);
        return AccountTransformer.toDto(savedAccount);
    }*/
   /*@Override
   public AccountDTO addAccount(Account account) throws Exception {
       Customer existingCustomer = customerRepository.findById(account.getCustomer().getId());
       if (existingCustomer == null) {
           throw new Exception("Customer with ID " + account.getCustomer().getId() + " not found.");
       }
       Account savedAccount = accountRepository.save(account);
       if (savedAccount == null) {
           throw new Exception("Failed to save account.");
       }
       return AccountTransformer.toDto(savedAccount);
   }*/
  /* @Override
   public AccountDTO addAccount(Account account) throws Exception {
       try {
           Customer existingCustomer = customerRepository.findById(account.getCustomer().getId());
           if (existingCustomer == null) {
              throw new NullPointerException("Customer with ID " + account.getCustomer().getId() + " not found.");
           }
           Account savedAccount = accountRepository.save(account);
           return AccountTransformer.toDto(savedAccount);
       } catch (NullPointerException e) {
           throw e;
       } catch (Exception e) {
           // Handle other exceptions gracefully
           log.error("Error occurred while adding account: {}", e.getMessage());
           throw new RuntimeException("Failed to add account.");
       }
   }*/
   @Override
   public AccountDTO addAccount(Account account) {

           Customer existingCustomer = customerRepository.findById(account.getCustomer().getId());
           if (existingCustomer == null) {

               return null;
           }
           Account savedAccount = accountRepository.save(account);
           return AccountTransformer.toDto(savedAccount);

   }





 /*   @Override
    public AccountDTO updateAccount(Account account, int accountId) {
        Account existingAccount = accountRepository.findById(accountId);
        if (existingAccount != null) {
            // Copy non-null properties from customer to existingCustomer
            BeanUtils.copyProperties(account, existingAccount, "id");
            accountRepository.save(existingAccount);
            return AccountTransformer.toDto(existingAccount);
        }
        return null;
    }*/

    @Override
    public AccountDTO updateAccount(Account account, int accountId) throws Exception {
        Customer existingCustomer = customerRepository.findById(account.getCustomer().getId());
        if (existingCustomer == null) {
            return null;
        }
        Account existingAccount = accountRepository.findById(accountId);
        if (existingAccount != null) {
            BeanUtils.copyProperties(account, existingAccount, "id");
            accountRepository.save(existingAccount);
            return AccountTransformer.toDto(existingAccount);
        }
        return null;
    }



    @Override
    public AccountDTO deleteAccountById(int id) {
        Account account = accountRepository.findById(id);
        System.out.println("Account:"+account);
        if (account != null) {
            accountRepository.deleteById(id);
            return AccountTransformer.toDto(account);
        }

        return null;
    }
}

package com.repository;


import com.entity.Account;
import com.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Integer> {
    public Account findById(int id);



    List<Account> findByCustomer_Id(int customerId);
}

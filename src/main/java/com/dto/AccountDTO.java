package com.dto;

import com.entity.Customer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class AccountDTO {

    private int id;
    private int customerId; // Added customerId field
    private String accNo;
    private String accType;
    private String pinCode;
    private Date dateOfCreate;
    private int amount;
    private String remarks;



}

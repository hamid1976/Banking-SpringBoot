package com.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;


    @Column(name="acc_no", unique = true)
    private String accNo;

    @Column(name="pin_code")
    private String pinCode;

    @Column(name="acc_type")
    private String accType;

    @Column(name="date_of_create")
    private Date dateOfCreate;

    private int amount;

    private String remarks;
}

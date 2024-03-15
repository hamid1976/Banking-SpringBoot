package com.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;


@Getter
@Setter
@ToString
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String caste;
    private String email;
    private String address;
    private String city;
    private String state;
    private String country;
    private String number;

    /*@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Account> accounts;*/

    @OneToMany(mappedBy = "customer", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH,CascadeType.REMOVE})
    private List<Account> accounts;

    public Customer() {
    }


}


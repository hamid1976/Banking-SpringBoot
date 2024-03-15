package com.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {

    private int id;
    private String name;
    private String email;
    private String number;
    private String caste;
    private String address;
    private String city;
    private String state;
    private  String country;

    public CustomerDTO() {
        // Default constructor
    }

    public CustomerDTO(String message) {
        this.name = message; // You can set the message to any field you prefer
    }

}

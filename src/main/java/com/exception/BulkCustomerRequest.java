package com.exception;

import com.dto.CustomerDTO;

import java.util.List;

public class BulkCustomerRequest {

    private List<CustomerDTO> customers;

    public List<CustomerDTO> getCustomers() {

        return customers;
    }

    public void setCustomers(List<CustomerDTO> customers) {

        this.customers = customers;
    }
}

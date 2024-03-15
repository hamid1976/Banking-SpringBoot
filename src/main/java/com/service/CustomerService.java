package com.service;

import com.dto.CustomerDTO;
import com.entity.Customer;

import java.util.List;

public interface CustomerService {

        List<CustomerDTO> getAllCustomer();

        CustomerDTO getCustomerById(int id);

        CustomerDTO addCustomer(Customer customer);


        CustomerDTO updateCustomer(Customer customer, int customerId);


        CustomerDTO deleteCustomerById(int id);

        List<CustomerDTO> addBulkCustomers(List<CustomerDTO> customers);

        List<CustomerDTO> updateBulkCustomers(List<CustomerDTO> customerDTOs);
}

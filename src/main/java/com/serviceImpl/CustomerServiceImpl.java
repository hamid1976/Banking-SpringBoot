package com.serviceImpl;

import com.dto.CustomerDTO;
import com.entity.Customer;
import com.repository.CustomerRepository;
import com.service.CustomerService;
import com.transformer.CustomerTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @Override
    public List<CustomerDTO> getAllCustomer() {
        List<Customer> customers = customerRepository.findAll();
        return CustomerTransformer.toDTO(customers);
    }//getAllCustomer
    @Override
    public CustomerDTO getCustomerById(int id) {
        Customer customer = customerRepository.findById(id);
        if (customer != null) {
            return CustomerTransformer.toDto(customer);
        }
        return null;
    }//GetCustomerById
    @Override
    public CustomerDTO addCustomer(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        return CustomerTransformer.toDto(savedCustomer);
    }//addCustomer


   @Override
   public CustomerDTO updateCustomer(Customer customer, int customerId) {
       Customer existingCustomer = customerRepository.findById(customerId);
       if (existingCustomer != null) {
           // Copy non-null properties from customer to existingCustomer
           BeanUtils.copyProperties(customer, existingCustomer, "id");
           customerRepository.save(existingCustomer);
           return CustomerTransformer.toDto(existingCustomer);
       }
       return null;
   }


    @Override
    public CustomerDTO deleteCustomerById(int id) {
        Customer customer = customerRepository.findById(id);
        if (customer != null) {
            customerRepository.deleteById(id);
            return CustomerTransformer.toDto(customer);
        } else {
            // Customer not found with the given ID
            return null;
        }
    }


      @Override
      public List<CustomerDTO> addBulkCustomers(List<CustomerDTO> customerDTOs) {
          List<CustomerDTO> addedCustomers = new ArrayList<>();

         for(int i=0; i<customerDTOs.size(); i++){
               try {
                   CustomerDTO dto=customerDTOs.get(i);
                   Customer savedCustomer = customerRepository.save(CustomerTransformer.toEntity(dto));
                   addedCustomers.add(CustomerTransformer.toDto(savedCustomer));
              } catch (Exception e) {
                  log.error("Error occurred while adding customer: {}", e.getMessage());
              }
          }
          return addedCustomers;
      }

    @Override
    public List<CustomerDTO> updateBulkCustomers(List<CustomerDTO> customerDTOs) {
        List<CustomerDTO> updatedCustomers = new ArrayList<>();
        for (CustomerDTO customerDTO : customerDTOs) {
            try {

                Customer existingCustomer = customerRepository.findById(customerDTO.getId());
                if (existingCustomer != null) {

                    Customer updatedCustomer = CustomerTransformer.toEntity(customerDTO);
                    BeanUtils.copyProperties(updatedCustomer, existingCustomer, "id");

                    Customer savedCustomer = customerRepository.save(existingCustomer);

                    CustomerDTO updatedCustomerDTO = CustomerTransformer.toDto(savedCustomer);

                    updatedCustomers.add(updatedCustomerDTO);
                } else {
                    log.error("Customer with ID {} not found for update", customerDTO.getId());
                }
            } catch (Exception e) {
                log.error("Error occurred while updating customer: {}", e.getMessage());
            }
        }
        return updatedCustomers;
    }
}

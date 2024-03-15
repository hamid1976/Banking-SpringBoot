package com.controller;

import java.util.List;;
import com.dto.CustomerDTO;
import com.entity.Customer;
import com.exception.BulkCustomerRequest;
import lombok.extern.slf4j.Slf4j;
import com.exception.ResponseUtils;
import com.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping("/getAll")
    public Object getAllCustomer() {
        try {
            List<CustomerDTO> list = customerService.getAllCustomer();
            if (list == null || list.isEmpty()) {
                return ResponseUtils.returnResponseForNull("Success");
            }
            return ResponseUtils.returnResponseWithCustomMessage(HttpStatus.OK.value(), "Success", list);
        } catch (Exception e) {
            log.error("Error occurred while fetching customers: {}", e.getMessage());
            return ResponseUtils.returnResponse(e);
        }

    }
    @GetMapping(path="/getById/{id}")
    public Object getCustomerById(@PathVariable int id) {
        try {
            CustomerDTO dto = customerService.getCustomerById(id);
            System.out.println(dto);
            if (dto==null) {
                return ResponseUtils.returnResponseForNull("Success");
            }else{

                return ResponseUtils.returnResponseWithCustomMessage(HttpStatus.OK.value(), "Success", dto);
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching customers: {}", e.getMessage());
            return ResponseUtils.returnResponse(e);
        }
    }
    @PostMapping(path = "/add")
    public Object addCustomer(@RequestBody Customer customer) {
        try {
            CustomerDTO customerDTO = customerService.addCustomer(customer);
            if (customerDTO == null) {
                return ResponseUtils.returnResponseForNull("Success");
            }
            return ResponseUtils.returnResponse(customerDTO);
        } catch (Exception e) {
            log.error("Error occurred while Adding customers: {}", e.getMessage());
            return ResponseUtils.returnResponse(e);
        }
    }
    @PostMapping(path = "/addBulk")
    public Object addBulkCustomers(@ModelAttribute BulkCustomerRequest bulkCustomerRequest) {
      try {
          List<CustomerDTO> customerDTOs = bulkCustomerRequest.getCustomers();

          List<CustomerDTO> addedCustomers = customerService.addBulkCustomers(customerDTOs);
          if (addedCustomers == null || addedCustomers.isEmpty()) {
              return ResponseUtils.returnResponseForNull("No customers added.");
          }
          return ResponseUtils.returnResponseForBulkOfCustomer(addedCustomers);
      } catch (Exception e) {
          log.error("Error occurred while adding customers in bulk: {}", e.getMessage());
          return ResponseUtils.returnResponse(e);
      }
    }//end addBulkCustomers
    @PostMapping(path="/update/{id}")
    public Object updateCustomer (@RequestBody Customer customer, @PathVariable("id")int customerId){
        try {
            CustomerDTO dto1 = customerService.updateCustomer(customer,customerId);
            System.out.println(dto1);
            if (dto1==null) {
                return ResponseUtils.returnResponseForNull("Success");
            }
            return ResponseUtils.updateResponse(dto1);
        } catch (Exception e) {
            log.error("Error occurred while updating customer: {}", e.getMessage());
            return ResponseUtils.returnResponse(e);
        }
    }
    @PostMapping("/updateBulk")
    public Object updateBulkCustomers(@ModelAttribute BulkCustomerRequest bulkCustomerRequest) {
        try {
            List<CustomerDTO> customerDTOs = bulkCustomerRequest.getCustomers();

            List<CustomerDTO> updatedCustomers = customerService.updateBulkCustomers(customerDTOs);

            if (updatedCustomers == null || updatedCustomers.isEmpty()) {
                return "No customers updated.";
            }
            return ResponseUtils.updateBulkResponse();
        } catch (Exception e) {
            return "Error occurred while updating customers: " + e.getMessage();
        }
    }

    @PostMapping(path="/delete/{id}")
    public Object deleteCustomerById(@PathVariable int id) {
        try {
            CustomerDTO dto = customerService.deleteCustomerById(id);
            System.out.println(dto);
            if (dto==null) {
                return ResponseUtils.returnResponseForNull("Success");
            }
            return ResponseUtils.deleteResponse(dto);
        } catch (Exception e) {
            log.error("Error occurred while fetching customers: {}", e.getMessage());
            return ResponseUtils.returnResponse(e);
        }
    }


}


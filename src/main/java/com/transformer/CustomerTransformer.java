package com.transformer;

import com.dto.CustomerDTO;
import com.entity.Customer;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerTransformer {

    public static CustomerDTO toDto(Customer entity) {
        CustomerDTO customerDTO = new CustomerDTO();
        if (entity.getId() != 0)
            customerDTO.setId(entity.getId());
        if (entity.getName() != null)
            customerDTO.setName(entity.getName());
        if (entity.getEmail() != null)
            customerDTO.setEmail(entity.getEmail());
        if (entity.getNumber() != null)
            customerDTO.setNumber(entity.getNumber());
        if (entity.getCaste() != null)
            customerDTO.setCaste(entity.getCaste());
        if (entity.getAddress() != null)
            customerDTO.setAddress(entity.getAddress());
        if(entity.getCity() != null)
            customerDTO.setCity(entity.getCity());
        if(entity.getState() != null)
            customerDTO.setState(entity.getState());
        if(entity.getCountry() != null)
            customerDTO.setCountry(entity.getCountry());

        return customerDTO;
    }

    public static Customer toEntity(CustomerDTO dto) {
        Customer customer = new Customer();
        if (dto.getId() != 0)
            customer.setId(dto.getId());
        if (dto.getName() != null)
            customer.setName(dto.getName());
        if (dto.getEmail() != null)
            customer.setEmail(dto.getEmail());
        if (dto.getNumber() != null)
            customer.setNumber(dto.getNumber());
        if (dto.getCaste() != null)
            customer.setCaste(dto.getCaste());
        if (dto.getAddress() != null)
            customer.setAddress(dto.getAddress());
        if(dto.getCity() != null)
            customer.setCity(dto.getCity());
        if(dto.getState() != null)
            customer.setState(dto.getState());
        if(dto.getCountry() != null)
            customer.setCountry(dto.getCountry());
        return customer;
    }

    public static List<Customer> toENTITY(List<CustomerDTO> dto){
        List<Customer> list=new ArrayList<>();
        if(!CollectionUtils.isEmpty(dto)){
            for(CustomerDTO c:dto){
               // customer.add((Customer) toEntity((List<CustomerDTO>) c));
                list.add(toEntity(c));
            }

        }
        return list;
    }


    public static List<CustomerDTO> toDTO(List<Customer> customer){
        List<CustomerDTO> list=new ArrayList<>();
        if(!CollectionUtils.isEmpty(customer)){
            for(Customer c:customer){
                list.add(toDto(c));
            }

        }
        return list;
    }


}

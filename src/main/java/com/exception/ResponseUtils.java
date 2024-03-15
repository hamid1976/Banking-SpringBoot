package com.exception;

import com.dto.AccountDTO;
import com.dto.CustomerDTO;
import com.dto.StatusDTO;
import com.entity.Account;
import com.enums.StatusEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

public class ResponseUtils{

    public static ResponseEntity<Object> returnResponse(RuntimeException exception) {
        StatusDTO statusDTO = new StatusDTO();
        statusDTO.setCode(StatusEnum.FAILURE);
        statusDTO.setStatusDescription(exception.getMessage());
        exception.printStackTrace();
        return new ResponseEntity<>(statusDTO, HttpStatus.OK);
    }

    public static ResponseEntity<Object> returnResponse(Exception exception) {
        StatusDTO statusDTO = new StatusDTO();
        statusDTO.setCode(StatusEnum.FAILURE);
        statusDTO.setStatusDescription(exception.getMessage());
        exception.printStackTrace();
        return new ResponseEntity<>(statusDTO, HttpStatus.OK);
    }


    public static ResponseEntity<Object> returnResponseWithCustomMessage(Integer code, String message, Object data) {
        StatusDTO statusDTO = new StatusDTO();

        statusDTO.setCode(StatusEnum.SUCCESS); // Use your default success code
        statusDTO.setStatusDescription(message);
        statusDTO.setData(data);
        return new ResponseEntity<>(statusDTO, HttpStatus.valueOf(code)); // Use HttpStatus.valueOf(code) directly
    }


    public static ResponseEntity<Object> returnResponse(MethodArgumentNotValidException exception) {
        exception.printStackTrace();
        StringBuilder message = new StringBuilder();
        for (ObjectError error : exception.getBindingResult().getAllErrors()) {
            message = message.append(error.getDefaultMessage() + "\n");
        }
        StatusDTO statusDTO = new StatusDTO();
        statusDTO.setCode(StatusEnum.FAILURE);
        statusDTO.setStatusDescription(exception.getMessage());
        return new ResponseEntity<>(statusDTO, HttpStatus.OK);
    }

    public static ResponseEntity<Object> returnResponse(Object object) {
        StatusDTO statusDTO = new StatusDTO();
        statusDTO.setCode(StatusEnum.SUCCESS);
        statusDTO.setStatusDescription(StatusEnum.SUCCESS.getReasonPhrase());
        statusDTO.setData("Customer Added Successfully");
        return new ResponseEntity<>(statusDTO, HttpStatus.OK);
    }
    public static ResponseEntity<Object> updateResponse(Object object) {
        StatusDTO statusDTO = new StatusDTO();
        statusDTO.setCode(StatusEnum.SUCCESS);
        statusDTO.setStatusDescription(StatusEnum.SUCCESS.getReasonPhrase());
        statusDTO.setData("Customer Updated Successfully");
        return new ResponseEntity<>(statusDTO, HttpStatus.OK);
    }

    public static ResponseEntity<Object> returnResponse(StatusDTO StatusDTO) {
        return new ResponseEntity<>(StatusDTO, HttpStatus.OK);
    }

    public static ResponseEntity<Object> returnResponseForNull(String message) {
        StatusDTO statusDTO = new StatusDTO();
        statusDTO.setCode(StatusEnum.SUCCESS); // Set status code as SUCCESS
        statusDTO.setStatusDescription(message); // Set status description as 0
        statusDTO.setData("Data not found"); // Set message in the data field
        return new ResponseEntity<>(statusDTO, HttpStatus.OK);
    }


    public static Object deleteResponse(Object object) {
        StatusDTO statusDTO = new StatusDTO();
        statusDTO.setCode(StatusEnum.SUCCESS);
        statusDTO.setStatusDescription(StatusEnum.SUCCESS.getReasonPhrase());
        statusDTO.setData("Customer Deleted Successfully");
        return new ResponseEntity<>(statusDTO, HttpStatus.OK);
    }

    public static ResponseEntity<Object> returnResponseForAccount(Object object) {
        StatusDTO statusDTO = new StatusDTO();
        statusDTO.setCode(StatusEnum.SUCCESS);
        statusDTO.setStatusDescription(StatusEnum.SUCCESS.getReasonPhrase());
        statusDTO.setData("Account Added Successfully");
        return new ResponseEntity<>(statusDTO, HttpStatus.OK);
    }


    public static ResponseEntity<Object> updateResponseForAccount(Object object) {
        StatusDTO statusDTO = new StatusDTO();
        statusDTO.setCode(StatusEnum.SUCCESS);
        statusDTO.setStatusDescription(StatusEnum.SUCCESS.getReasonPhrase());
        statusDTO.setData("Account  Updated Successfully");
        return new ResponseEntity<>(statusDTO, HttpStatus.OK);
    }


    public static Object deleteResponseForAccount(Object object) {
        StatusDTO statusDTO = new StatusDTO();
        statusDTO.setCode(StatusEnum.SUCCESS);
        statusDTO.setStatusDescription(StatusEnum.SUCCESS.getReasonPhrase());
        statusDTO.setData("Account Deleted Successfully");
        return new ResponseEntity<>(statusDTO, HttpStatus.OK);
    }


    public static Object customerNotFound(Account account) {
        StatusDTO statusDTO = new StatusDTO();
        statusDTO.setCode(StatusEnum.SUCCESS);
        statusDTO.setStatusDescription(StatusEnum.SUCCESS.getReasonPhrase());
        statusDTO.setData("Customer Not Found with id:"+account.getCustomer().getId());
        return new ResponseEntity<>(statusDTO, HttpStatus.OK);
    }

    public static Object returnResponseForBulkOfCustomer(List<CustomerDTO> addedCustomers) {
        StatusDTO statusDTO = new StatusDTO();
        statusDTO.setCode(StatusEnum.SUCCESS);
        statusDTO.setStatusDescription(StatusEnum.SUCCESS.getReasonPhrase());
        statusDTO.setData(addedCustomers);
        return new ResponseEntity<>(statusDTO, HttpStatus.OK);
    }

    public static Object updateBulkResponse() {
        StatusDTO statusDTO = new StatusDTO();
        statusDTO.setCode(StatusEnum.SUCCESS);
        statusDTO.setStatusDescription(StatusEnum.SUCCESS.getReasonPhrase());
        statusDTO.setData("Bulk Of Customers Updated Successfully");
        return new ResponseEntity<>(statusDTO, HttpStatus.OK);
    }
}


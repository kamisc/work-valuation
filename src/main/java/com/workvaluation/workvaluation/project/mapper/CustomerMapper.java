package com.workvaluation.workvaluation.project.mapper;

import com.workvaluation.workvaluation.project.domain.Customer;
import com.workvaluation.workvaluation.project.dto.CustomerDTO;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public CustomerDTO mapToCustomerDTO(Customer customer) {
        return new CustomerDTO(
                customer.getFirstName(),
                customer.getLastName(),
                customer.getAddress(),
                customer.getEmail(),
                customer.getPhoneNumber());
    }

    public Customer mapToCustomer(CustomerDTO customerDTO) {
        return new Customer(
                customerDTO.getFirstName(),
                customerDTO.getLastName(),
                customerDTO.getAddress(),
                customerDTO.getEmail(),
                customerDTO.getPhoneNumber());
    }
}

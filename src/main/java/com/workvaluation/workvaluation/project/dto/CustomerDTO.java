package com.workvaluation.workvaluation.project.dto;

import lombok.Data;

@Data
public final class CustomerDTO {
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String email;
    private final String phoneNumber;
}

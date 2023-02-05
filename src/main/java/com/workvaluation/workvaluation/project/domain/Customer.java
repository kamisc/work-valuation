package com.workvaluation.workvaluation.project.domain;

import lombok.Data;

@Data
public final class Customer {
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String email;
    private final String phoneNumber;
}

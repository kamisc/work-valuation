package com.workvaluation.workvaluation.project.dto;

import lombok.Data;

@Data
public class ProjectDTO {
    private Long id;
    private String name;
    private CustomerDTO customerDTO;

    public ProjectDTO(Long id, String name, CustomerDTO customerDTO) {
        this.id = id;
        this.name = name;
        this.customerDTO = customerDTO;
    }

    public ProjectDTO(String name, CustomerDTO customerDTO) {
        this.name = name;
        this.customerDTO = customerDTO;
    }
}

package com.workvaluation.workvaluation.project.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ProjectDTO {
    private Long id;
    private String name;
    private String address;
    private Date createDate;

    public ProjectDTO(Long id, String name, String address, Date createDate) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.createDate = createDate;
    }

    public ProjectDTO(String name, String address) {
        this.name = name;
        this.address = address;
    }
}

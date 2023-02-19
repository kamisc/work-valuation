package com.workvaluation.workvaluation.renovationproject.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class RenovationProjectDTO {
    private Long id;
    private String name;
    private String address;
    private Date createDate;

    public RenovationProjectDTO(final Long id, final String name, final String address, final Date createDate) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.createDate = createDate;
    }

    public RenovationProjectDTO(String name, String address) {
        this.name = name;
        this.address = address;
    }
}

package com.workvaluation.workvaluation.renovationproject.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "RENOVATION_PROJECT")
public class RenovationProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private String address;
    private Date createDate;

    public RenovationProjectEntity(final String name, final String address, final Long id) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.createDate = new Date();
    }

    public RenovationProjectEntity(final String name, final String address) {
        this.name = name;
        this.createDate = new Date();
        this.address = address;
    }
}

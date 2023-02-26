package com.sewerynkamil.workvaluation.renovationproject.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "RENOVATION_PROJECT")
public class RenovationProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private String address;
    private Date createDate;

    public RenovationProject(final String name, final String address, final Long id) {
        this(name, address);
        this.id = id;
        this.createDate = new Date();
    }

    public RenovationProject(final String name, final String address) {
        this.name = name;
        this.createDate = new Date();
        this.address = address;
    }
}

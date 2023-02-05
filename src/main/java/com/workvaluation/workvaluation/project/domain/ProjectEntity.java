package com.workvaluation.workvaluation.project.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "project")
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Date createDate;
    private Customer customer;
    public ProjectEntity(
            final Long id,
            final String name,
            final Customer customer) {
        this.id = id;
        this.name = name;
        this.createDate = new Date();
        this.customer = customer;
    }

    public ProjectEntity(
            final String name,
            final Customer customer) {
        this.name = name;
        this.createDate = new Date();
        this.customer = customer;
    }
}

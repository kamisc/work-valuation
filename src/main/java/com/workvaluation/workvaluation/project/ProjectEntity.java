package com.workvaluation.workvaluation.project;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "project")
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    private final String name;
    private final Date createDate;
    private final String clientName;
    private final String address;
    private final String email;

    public ProjectEntity(
            final Long id,
            final String name,
            final String clientName,
            final String address,
            final String email) {
        this.id = id;
        this.name = name;
        this.createDate = new Date();
        this.clientName = clientName;
        this.address = address;
        this.email = email;
    }
}

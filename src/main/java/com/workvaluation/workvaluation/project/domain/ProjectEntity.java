package com.workvaluation.workvaluation.project.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "project")
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private String address;
    private Date createDate;

    public ProjectEntity(final String name, final String address) {
        this.name = name;
        this.createDate = new Date();
        this.address = address;
    }
}

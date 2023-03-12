package com.sewerynkamil.workvaluation.renovationwork.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "RENOVATION_WORK_TYPE")
public class RenovationWorkType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Date createDate;

    public RenovationWorkType(final Long id, final String name) {
        this.id = id;
        this.name = name;
        this.createDate = new Date();
    }
}

package com.usyd.emergency.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@Table(name = "disease")
public class Disease {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "disease_id", nullable = false)
    private int diseaseId;

    @Column(name = "disease_name")
    private String diseaseName;

    @Column(name = "level")
    private int level;


}

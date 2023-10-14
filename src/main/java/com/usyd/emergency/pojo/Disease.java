package com.usyd.emergency.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("disease_id")
    private int diseaseId;

    @Column(name = "disease_name")
    @JsonProperty("disease_name")
    private String diseaseName;

    @Column(name = "level")
    @JsonProperty("disease_level")
    private int level;


}

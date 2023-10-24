package com.usyd.emergency.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "dcase")
public class Case {

    @Column(name = "disease_id", nullable = false)
    @JsonProperty("disease_id")
    private int diseaseId;

    @Column(name = "longitude", nullable = false)
    @JsonProperty("longitude")
    private String longitude;

    @Column(name = "latitude", nullable = false)
    @JsonProperty("latitude")
    private String latitude;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "case_id", nullable = false)
    @JsonProperty("case_id")
    private int caseId;

}
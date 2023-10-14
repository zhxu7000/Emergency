package com.usyd.emergency.pojo;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "subscribes")
public class Subscribes {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "disease_id", nullable = false)
    private int diseaseId;

    private int preference;
    public void setPreference(int preference) {
        this.preference = preference;
    }
}
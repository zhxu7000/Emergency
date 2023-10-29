package com.usyd.emergency.pojo;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.usyd.emergency.utils.MyKey;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@IdClass(MyKey.class)
@Table(name = "subscribes")
public class Subscribes {
    @Id
    @Column(name = "user_id", nullable = false)
    @JsonProperty("user_id")
    private int userId;
    @Id
    @Column(name = "disease_id", nullable = false)
    @JsonProperty("disease_id")
    private int diseaseId;

    public Subscribes(int userId, int diseaseId) {
        this.userId = userId;
        this.diseaseId = diseaseId;
    }

    public Subscribes() {
    }
}
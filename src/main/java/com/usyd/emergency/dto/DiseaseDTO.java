package com.usyd.emergency.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DiseaseDTO {
    public static class addDiseaseDTO {

        @JsonProperty("disease_name")
        public String diseaseName;
        @JsonProperty("disease_level")
        public Integer diseaseLevel;
    }
}

package com.usyd.emergency.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class CaseDTO {

    public static class addCaseDTO {
        @JsonProperty("disease_id")
        public int disease_id;
        @JsonProperty("longitude")
        public BigDecimal longitude;
        @JsonProperty("latitude")
        public BigDecimal latitude;
    }

    public static class updateCaseDTO {
        @JsonProperty("disease_id")
        public int disease_id;
        @JsonProperty("longitude")
        public  BigDecimal longitude;
        @JsonProperty("latitude")
        public BigDecimal latitude;
        @JsonProperty("case_id")
        public Integer caseId;
    }

    public static class locationDTO {
        @JsonProperty("from_longitude")
        public  BigDecimal fromLongitude;
        @JsonProperty("from_latitude")
        public BigDecimal fromLatitude;
        @JsonProperty("to_longitude")
        public  BigDecimal toLongitude;
        @JsonProperty("to_latitude")
        public BigDecimal toLatitude;
    }

}
package com.usyd.emergency.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class CaseDTO {

    public static class addCaseDTO {
        @JsonProperty("disease_id")
        public int disease_id;
        @JsonProperty("location")
        public String location;

    }

    public static class updateCaseDTO {
        @JsonProperty("disease_id")
        public int disease_id;
        @JsonProperty("location")
        public  String location;
        @JsonProperty("case_id")
        public Integer caseId;
    }

    public static class locationDTO {
        @JsonProperty("from_longitude")
        public  String fromLongitude;
        @JsonProperty("from_latitude")
        public String fromLatitude;
        @JsonProperty("to_longitude")
        public  String toLongitude;
        @JsonProperty("to_latitude")
        public String toLatitude;
    }

}
package com.usyd.emergency.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubscriptionDTO {
    public static class addSubscriptionDTO {
        @JsonProperty("user_id")
        public Integer user_id;
        @JsonProperty("disease_id")
        public Integer disease_id;
    }

    public static class deleteSubscriptionDTO {
        @JsonProperty("user_id")
        public Integer user_id;
        @JsonProperty("disease_id")
        public Integer disease_id;
    }
}

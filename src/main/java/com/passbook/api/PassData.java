package com.passbook.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class PassData {

    @JsonProperty
    private final Map<String, String> data;

    public PassData(@JsonProperty("data") final Map<String, String> data) {
        this.data = data;
    }

    public Map<String, String> getData() {
        return data;
    }
}

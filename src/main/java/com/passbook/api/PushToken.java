package com.passbook.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

public class PushToken {

    @JsonProperty
    private final String pushToken;


    public PushToken(@JsonProperty("pushToken") String pushToken) {
        this.pushToken = pushToken;
    }

    public String getPushToken() {
        return pushToken;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("pushToken", pushToken)
                .toString();
    }
}

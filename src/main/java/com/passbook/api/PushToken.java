package com.passbook.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class PushToken {

    @NotEmpty
    @JsonProperty
    @Length(max = 255)
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

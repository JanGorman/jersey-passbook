package com.passbook.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

public class Pass {

    @JsonProperty
    private final long updatedAt;

    @JsonProperty
    private final String serialNumber;

    public Pass(@JsonProperty("updatedAt") final long updatedAt, @JsonProperty("serialNumber") final String serialNumber) {
        this.updatedAt = updatedAt;
        this.serialNumber = serialNumber;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("updatedAt", updatedAt)
                .add("serialNumber", serialNumber)
                .toString();
    }
}

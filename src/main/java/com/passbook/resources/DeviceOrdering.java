package com.passbook.resources;

import com.google.common.base.Function;
import com.passbook.core.Device;

import javax.annotation.Nullable;
import java.sql.Timestamp;

public enum DeviceOrdering implements Function<Device, Timestamp> {

    GET_UPDATED_AT {
        @Override
        public Timestamp apply(Device input) {
            return input.getUpdatedAt();
        }
    };

    public abstract Timestamp apply(@Nullable Device input);
}
package com.passbook.core;

import com.google.common.base.Objects;

public class Registration {

    private long id;

    // device_library_identifier_idx
    private String deviceLibraryIdentifier;

    private String pushToken;

    private long createdAt;

    private long updatedAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDeviceLibraryIdentifier() {
        return deviceLibraryIdentifier;
    }

    public void setDeviceLibraryIdentifier(String deviceLibraryIdentifier) {
        this.deviceLibraryIdentifier = deviceLibraryIdentifier;
    }

    public String getPushToken() {
        return pushToken;
    }

    public void setPushToken(String pushToken) {
        this.pushToken = pushToken;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("deviceLibraryIdentifier", deviceLibraryIdentifier)
                .add("pushToken", pushToken)
                .add("createdAt", createdAt)
                .add("updatedAt", updatedAt)
                .toString();
    }
}

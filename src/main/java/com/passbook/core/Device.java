package com.passbook.core;

import com.google.common.base.Objects;
import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Set;

public class Device {

    private long id;

    // pass_type_identifier_idx
    private String passTypeIdentifier;

    // serial_number_idx
    private String serialNumber;

    private String authenticationToken;

    //    @Type(type = "hstore")
    private Map<String, String> data = Maps.newHashMap();

    private long createdAt;

    private long updatedAt;

    private Set<Registration> registrations;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassTypeIdentifier() {
        return passTypeIdentifier;
    }

    public void setPassTypeIdentifier(String passTypeIdentifier) {
        this.passTypeIdentifier = passTypeIdentifier;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getAuthenticationToken() {
        return authenticationToken;
    }

    public void setAuthenticationToken(String authenticationToken) {
        this.authenticationToken = authenticationToken;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
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

    public Set<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(Set<Registration> registrations) {
        this.registrations = registrations;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("passTypeIdentifier", passTypeIdentifier)
                .add("serialNumber", serialNumber)
                .add("authenticationToken", authenticationToken)
                .add("data", data)
                .add("createdAt", createdAt)
                .add("updatedAt", updatedAt)
                .add("registrations", registrations)
                .toString();
    }
}

package com.passbook.core;

import com.google.common.base.Objects;
import com.google.common.collect.Maps;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "passbook_devices")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "pass_type_identifier", nullable = false)
    private String passTypeIdentifier;

    @Column(name = "serial_number", nullable = false)
    private String serialNumber;

    @Column(name = "authentication_token", nullable = false)
    private String authenticationToken;

    @Type(type = "hstore")
    @Column(name = "data")
    private Map<String, String> data = Maps.newHashMap();

    @Type(type = "timestamp")
    @Column(name = "created_at", nullable = false)
    private long createdAt;

    @Type(type = "timestamp")
    @Column(name = "updated_at", nullable = false)
    private long updatedAt;

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
                .toString();
    }
}

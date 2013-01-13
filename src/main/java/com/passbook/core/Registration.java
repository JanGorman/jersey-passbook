package com.passbook.core;

import com.google.common.base.Objects;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "passbook_registrations")
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Index(name = "device_library_identifier_idx")
    @Column(name = "device_library_identifier", nullable = false)
    private String deviceLibraryIdentifier;

    @Column(name = "push_token", nullable = false)
    private String pushToken;

    @Type(type = "timestamp")
    @Column(name = "created_at", nullable = false)
    private long createdAt;

    @Type(type = "timestamp")
    @Column(name = "updated_at", nullable = false)
    private long updatedAt;

    @ManyToOne
    @JoinColumn(name = "id")
    private Device device;

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

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("deviceLibraryIdentifier", deviceLibraryIdentifier)
                .add("pushToken", pushToken)
                .add("createdAt", createdAt)
                .add("updatedAt", updatedAt)
                .add("device", device)
                .toString();
    }
}

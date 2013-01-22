package com.passbook.core;

import com.google.common.base.Objects;
import org.hibernate.annotations.Index;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "passbook_registrations")
@NamedQueries({
        @NamedQuery(
                name = "com.passbook.core.Registration.destroy",
                query = "DELETE Registration WHERE deviceLibraryIdentifier = :deviceLibraryIdentifier"
        )
})
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Index(name = "device_library_identifier_idx")
    @Column(name = "device_library_identifier", nullable = false)
    private String deviceLibraryIdentifier;

    @Column(name = "push_token", nullable = false)
    private String pushToken;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @ManyToOne
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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
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

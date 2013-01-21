package com.passbook.core;

import com.google.common.base.Objects;
import com.google.common.collect.Maps;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Map;

@Entity
@Table(name = "passbook_devices")
@TypeDef(name = "hstore", typeClass = HstoreUserType.class)
@NamedQueries({
        @NamedQuery(
                name = "com.passbook.core.Device.findByPassTypeIdentifierAndSerialNumber",
                query = "SELECT d FROM Device d WHERE d.passTypeIdentifier = :passTypeIdentifier " +
                        "AND d.serialNumber = :serialNumber"
        ),
        @NamedQuery(
                name = "com.passbook.core.Device.findByPassTypeIdentifierAndDeviceLibraryIdentifier",
                query = "SELECT d FROM Device d WHERE d.passTypeIdentifier = :passTypeIdentifier "
//                        "JOIN Registration r ON d.id = r.id " +
//                        "AND r.deviceLibraryIdentifier = :deviceLibraryIdentifier"
        )
})
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Index(name = "pass_type_identifier_idx")
    @Column(name = "pass_type_identifier", nullable = false)
    private String passTypeIdentifier;

    @Index(name = "serial_number_idx")
    @Column(name = "serial_number", nullable = false)
    private String serialNumber;

    @Column(name = "authentication_token", nullable = false)
    private String authenticationToken;

    @Type(type = "hstore")
    @Column(name = "data")
    private Map<String, String> data = Maps.newHashMap();

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

//    private Set<Registration> registrations;

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

    //    public Set<Registration> getRegistrations() {
//        return registrations;
//    }
//
//    public void setRegistrations(Set<Registration> registrations) {
//        this.registrations = registrations;
//    }

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
//                .add("registrations", registrations)
                .toString();
    }
}

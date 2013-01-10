package com.passbook.core;

import javax.persistence.*;

/**
 * User: jangorman
 * Date: 10.01.13
 */
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

}

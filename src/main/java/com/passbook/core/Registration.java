package com.passbook.core;

import javax.persistence.*;

/**
 * User: jangorman
 * Date: 10.01.13
 */
@Entity
@Table(name = "passbook_registrations")
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "device_library_identifier", nullable = false)
    private String deviceLibraryIdentifier;

    @Column(name = "push_token", nullable = false)
    private String pushToken;

}

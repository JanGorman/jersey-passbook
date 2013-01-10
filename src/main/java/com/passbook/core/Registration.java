package com.passbook.core;

import javax.persistence.*;

/**
 * User: jangorman
 * Date: 10.01.13
 */
@Entity
@Table(name = "registrations")
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

}

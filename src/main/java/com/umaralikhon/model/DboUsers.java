package com.umaralikhon.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "dbo_users")
@Data
public class DboUsers extends DboBaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "customer_id")
    private Long customerId;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "status")
    private Status status;

    @Column(name = "registered_date")
    private Date registeredDate;

    @Column(name = "abs_id")
    private String absId;

    @Column(name = "full_name")
    private String fullName = firstname + lastname;

    @Column(name = "username")
    private String username;

    @Column(name = "is_resident")
    private Boolean isResident;

    @Column(name = "pass_serial")
    private String passSerial;

    @Column(name = "pass_number")
    private String passNumber;

    @Column(name = "tin")
    private String tin;

    @Column(name = "pinfl")
    private String pinfl;

}

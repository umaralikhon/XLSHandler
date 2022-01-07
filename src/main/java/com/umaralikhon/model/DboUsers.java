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

    private Long customerId;

    private Long login;

    private String firstname;

    private String lastname;

    private Status status;

    private Date date;
}

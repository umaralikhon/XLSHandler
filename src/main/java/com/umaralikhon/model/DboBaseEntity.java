package com.umaralikhon.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class DboBaseEntity {
    @JsonIgnore
    @JsonIgnoreProperties
    @Enumerated(EnumType.STRING)
    private BaseStatus zStatus = BaseStatus.ACTIVE;
}

package com.softtech.marketapi.generic.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {

    @Embedded
    private BaseAdditionalFields baseAdditionalFields;
}

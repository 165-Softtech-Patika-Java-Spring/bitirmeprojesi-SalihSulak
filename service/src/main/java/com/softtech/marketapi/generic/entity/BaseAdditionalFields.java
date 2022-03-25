package com.softtech.marketapi.generic.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Embeddable
@Getter
@Setter
public class BaseAdditionalFields {

    @Column(name = "created_at", updatable = false)
    @CreatedDate
    private String createDate;

    @Column(name = "updated_at")
    @LastModifiedDate
    private String updateDate;

}

package com.softtech.marketapi.entity;

import com.softtech.marketapi.enums.ProductType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "vat_rates")
public class VatRate {

    @Id
    @Enumerated(EnumType.STRING)
    private ProductType productType;

    private short vatPercentage;

}

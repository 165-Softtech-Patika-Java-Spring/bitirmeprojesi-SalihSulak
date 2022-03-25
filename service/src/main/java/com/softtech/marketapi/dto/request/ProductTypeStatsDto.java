package com.softtech.marketapi.dto.request;

import com.softtech.marketapi.enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class ProductTypeStatsDto {

    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private double averagePrice;
    private long productCount;
    private ProductType productType;
    private short vat_percentage;
}

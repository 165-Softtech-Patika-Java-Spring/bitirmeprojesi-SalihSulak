package com.softtech.marketapi.dto.response;

import com.softtech.marketapi.enums.ProductType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class ProductResponseDto {
    private UUID id;
    private String productName;
    private ProductType productType;
    private BigDecimal priceWithoutVat;
    private BigDecimal priceWithVat;
}

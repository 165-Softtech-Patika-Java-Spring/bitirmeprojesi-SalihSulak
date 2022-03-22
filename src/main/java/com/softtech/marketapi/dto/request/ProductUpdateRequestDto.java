package com.softtech.marketapi.dto.request;

import com.softtech.marketapi.enums.ProductType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class ProductUpdateRequestDto {
    private UUID id;
    private String productName;
    private ProductType productType;
    private BigDecimal priceWithoutVat;
}

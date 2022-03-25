package com.softtech.marketapi.util;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PriceUtil {

    public BigDecimal getPriceWithVat(BigDecimal priceWithoutVat, short vatPercentage) {
        BigDecimal procces = BigDecimal.valueOf(vatPercentage).divide(BigDecimal.valueOf(100.0));
        BigDecimal vatPrice = priceWithoutVat.multiply(procces);
        return priceWithoutVat.add(vatPrice);
    }

}

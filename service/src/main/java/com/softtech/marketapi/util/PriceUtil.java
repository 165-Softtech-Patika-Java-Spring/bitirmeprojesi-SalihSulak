package com.softtech.marketapi.util;

import com.softtech.marketapi.entity.Product;
import com.softtech.marketapi.enums.ProductType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PriceUtil {

    public BigDecimal getPriceWithVat(BigDecimal priceWithoutVat, short vatRate) {
        BigDecimal procces = BigDecimal.valueOf(vatRate).divide(BigDecimal.valueOf(100.0));
        BigDecimal vatPrice = priceWithoutVat.multiply(procces);
        return priceWithoutVat.add(vatPrice);
    }

}

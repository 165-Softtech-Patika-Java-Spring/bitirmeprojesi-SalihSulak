package com.softtech.marketapi.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.parameters.P;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PriceUtilTest {

    @Test
    void shouldGetPriceWithVat() {
        PriceUtil priceUtil = new PriceUtil();
        short vatPercentage = 10;
        BigDecimal priceWithoutVat = BigDecimal.valueOf(100);

        BigDecimal priceWithVat = priceUtil.getPriceWithVat(priceWithoutVat, vatPercentage);
        BigDecimal expected = BigDecimal.valueOf(110.0);
        Assertions.assertEquals(expected,priceWithVat);
    }
}

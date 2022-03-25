package com.softtech.marketapi.controller;

import com.softtech.marketapi.entity.VatRate;
import com.softtech.marketapi.enums.ProductType;
import com.softtech.marketapi.generic.dto.RestResponse;
import com.softtech.marketapi.service.VatRateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VatRateControllerTest {

    @Mock
    VatRateService vatRateService;

    @InjectMocks
    VatRateController vatRateController;


    @Test
    void updateVatRate() {
        VatRate vatRate = mock(VatRate.class);
        vatRate.setProductType(ProductType.FOOD);
        vatRate.setVatPercentage((short) 3);
        ResponseEntity<RestResponse<VatRate>> result = vatRateController.updateVatRate(vatRate);
        assertEquals(200,result.getStatusCodeValue());
    }
}

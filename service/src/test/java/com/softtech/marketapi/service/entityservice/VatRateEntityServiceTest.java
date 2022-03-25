package com.softtech.marketapi.service.entityservice;

import com.softtech.marketapi.entity.VatRate;
import com.softtech.marketapi.enums.ProductType;
import com.softtech.marketapi.repository.VatRateRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VatRateEntityServiceTest {

    @Mock
    VatRateRepository vatRateRepository;

    @InjectMocks
    VatRateEntityService vatRateEntityService;

    @Test
    void shouldFindVatRateByProductType() {
        short returnValue = 5;
        when(vatRateRepository.findVatPercentageByProductType(any())).thenReturn(returnValue);
        short result = vatRateEntityService.findVatRateByProductType(any());
        assertEquals(5,result);
    }

    @Test
    void shouldUpdateVatRate() {
        VatRate vatRate = mock(VatRate.class);
        vatRate.setProductType(ProductType.FOOD);
        vatRate.setVatPercentage((short) 5);

        when(vatRateRepository.save(vatRate)).thenReturn(vatRate);
        VatRate result = vatRateEntityService.updateVatRate(vatRate);
        assertEquals(vatRate.getVatPercentage(),result.getVatPercentage());
    }
}

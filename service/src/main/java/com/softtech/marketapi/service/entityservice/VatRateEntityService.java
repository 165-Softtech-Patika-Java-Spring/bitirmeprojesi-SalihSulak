package com.softtech.marketapi.service.entityservice;

import com.softtech.marketapi.entity.VatRate;
import com.softtech.marketapi.enums.ProductType;
import com.softtech.marketapi.generic.exceptions.GenericBusinessException;
import com.softtech.marketapi.repository.VatRateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class VatRateEntityService {

    private final VatRateRepository vatRepository;


    public short findVatRateByProductType(ProductType productType){
        short vatRate = vatRepository.findVatPercentageByProductType(productType);
        return vatRate;
    }


    public VatRate updateVatRate(VatRate vatRate){
        vatRepository.save(vatRate);
        return vatRate;
    }

}

package com.softtech.marketapi.service;

import com.softtech.marketapi.entity.Product;
import com.softtech.marketapi.entity.VatRate;
import com.softtech.marketapi.enums.ProductType;
import com.softtech.marketapi.enums.errors.VatRateErrorMessages;
import com.softtech.marketapi.generic.exceptions.GenericBusinessException;
import com.softtech.marketapi.service.entityservice.ProductEntityService;
import com.softtech.marketapi.service.entityservice.VatRateEntityService;
import com.softtech.marketapi.util.PriceUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VatRateService {

    private final VatRateEntityService entityService;
    private final ProductEntityService productEntityService;
    private final PriceUtil priceUtil;

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateVatRate(VatRate vatRate){
        if (vatRate.getVatPercentage()<=0)
            throw new GenericBusinessException(VatRateErrorMessages.VAT_RATE_NOT_POSITIVE);

        entityService.updateVatRate(vatRate);
        ProductType productType = vatRate.getProductType();
        short vatPercentage = vatRate.getVatPercentage();
        List<Product> productList = productEntityService.findByProductType(productType);

        productList.forEach(product -> {

            BigDecimal priceWithoutVat = product.getPriceWithoutVat();
            BigDecimal priceWithVat = priceUtil.getPriceWithVat(priceWithoutVat, vatPercentage);

            product.setPriceWithVat(priceWithVat);
            productEntityService.saveProduct(product);
        });
    }


}

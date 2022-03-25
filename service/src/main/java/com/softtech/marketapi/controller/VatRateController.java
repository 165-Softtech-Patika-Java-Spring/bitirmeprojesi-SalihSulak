package com.softtech.marketapi.controller;

import com.softtech.marketapi.entity.VatRate;
import com.softtech.marketapi.generic.dto.RestResponse;
import com.softtech.marketapi.service.VatRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vat-rates")
public class VatRateController {

    private final VatRateService vatRateService;

    @PutMapping
    public ResponseEntity<RestResponse<VatRate>> updateVatRate(@RequestBody VatRate vatRate){
        vatRateService.updateVatRate(vatRate);
        return ResponseEntity.ok(RestResponse.of(vatRate));
    }
}

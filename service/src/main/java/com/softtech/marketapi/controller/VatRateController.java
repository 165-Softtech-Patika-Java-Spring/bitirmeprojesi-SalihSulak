package com.softtech.marketapi.controller;

import com.softtech.marketapi.entity.VatRate;
import com.softtech.marketapi.generic.dto.RestResponse;
import com.softtech.marketapi.service.VatRateService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/vat-rates")
public class VatRateController {

    private final VatRateService vatRateService;

    @PutMapping
    @Operation(tags = "Vat Rate Controller")
    public ResponseEntity<RestResponse<VatRate>> updateVatRate(@RequestBody VatRate vatRate){
        vatRateService.updateVatRate(vatRate);
        return ResponseEntity.ok(RestResponse.of(vatRate));
    }
}

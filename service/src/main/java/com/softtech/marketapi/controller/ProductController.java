package com.softtech.marketapi.controller;

import com.softtech.marketapi.dto.request.ProductTypeStatsDto;
import com.softtech.marketapi.dto.request.ProductDto;
import com.softtech.marketapi.dto.request.ProductUpdateRequestDto;
import com.softtech.marketapi.dto.response.ProductResponseDto;
import com.softtech.marketapi.enums.ProductType;
import com.softtech.marketapi.generic.dto.RestResponse;
import com.softtech.marketapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<RestResponse<ProductResponseDto>> saveProduct(@RequestBody ProductDto productDto){
        ProductResponseDto productResponseDto = productService.saveProduct(productDto);
        return ResponseEntity.ok(RestResponse.of(productResponseDto));
    }

    @PutMapping
    public ResponseEntity<RestResponse<ProductResponseDto>> updateProduct(@RequestBody ProductUpdateRequestDto updateRequest){
        ProductResponseDto productResponseDto = productService.updateProduct(updateRequest);
        return ResponseEntity.ok(RestResponse.of(productResponseDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestResponse<UUID>> deleteProduct(@PathVariable UUID id){
        UUID deletedProductId = productService.deleteProduct(id);
        return ResponseEntity.ok(RestResponse.of(deletedProductId));
    }

    @GetMapping
    public ResponseEntity<RestResponse<List<ProductResponseDto>>> listProductsWithFilter(
            @RequestParam(name = "start_price",required = false) Optional<Short> startPrice,
            @RequestParam(name = "end_price",required = false) Optional<Short> endPrice
    ){
        List<ProductResponseDto> productList = productService.listProductsWithFilter(startPrice,endPrice);
        return ResponseEntity.ok(RestResponse.of(productList));
    }

    @GetMapping("/{productType}")
    public ResponseEntity<RestResponse<List<ProductResponseDto>>> listProductsByProductType(@PathVariable ProductType productType){
        List<ProductResponseDto> productList = productService.findByProductType(productType);
        return ResponseEntity.ok(RestResponse.of(productList));
    }

    @GetMapping("/{productType}/analysis")
    public ResponseEntity<RestResponse<List<ProductTypeStatsDto>>> listTest(@PathVariable ProductType productType){
        List<ProductTypeStatsDto> minPriceByProductType = productService.getProductTypeStats(productType);
        return ResponseEntity.ok(RestResponse.of(minPriceByProductType));
    }


}

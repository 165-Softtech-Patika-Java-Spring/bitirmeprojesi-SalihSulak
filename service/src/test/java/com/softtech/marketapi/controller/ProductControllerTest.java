package com.softtech.marketapi.controller;

import com.softtech.marketapi.dto.request.ProductDto;
import com.softtech.marketapi.dto.request.ProductTypeStatsDto;
import com.softtech.marketapi.dto.request.ProductUpdateRequestDto;
import com.softtech.marketapi.dto.response.ProductResponseDto;
import com.softtech.marketapi.generic.dto.RestResponse;
import com.softtech.marketapi.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    ProductService productService;

    @InjectMocks
    ProductController productController;

    @Test
    void shouldSaveProduct() {
        ProductDto productDto = mock(ProductDto.class);
        ProductResponseDto productResponseDto = mock(ProductResponseDto.class);
        when(productService.saveProduct(productDto)).thenReturn(productResponseDto);
        ResponseEntity<RestResponse<ProductResponseDto>> result = productController.saveProduct(productDto);
        assertEquals(200,result.getStatusCodeValue());
    }

    @Test
    void shouldUpdateProduct() {
        ProductUpdateRequestDto productUpdateRequestDto = mock(ProductUpdateRequestDto.class);
        ProductResponseDto productResponseDto = mock(ProductResponseDto.class);
        when(productService.updateProduct(productUpdateRequestDto)).thenReturn(productResponseDto);
        ResponseEntity<RestResponse<ProductResponseDto>> result = productController.updateProduct(productUpdateRequestDto);
        assertEquals(200,result.getStatusCodeValue());

    }

    @Test
    void shouldDeleteProduct() {
        UUID id = UUID.randomUUID();
        when(productService.deleteProduct(id)).thenReturn(id);
        ResponseEntity<RestResponse<UUID>> result = productController.deleteProduct(id);
        assertEquals(200,result.getStatusCodeValue());

    }

    @Test
    void shouldListProductsWithFilter_whenParametersEmpty() {
        List<ProductResponseDto> productList = new ArrayList<>(List.of(mock(ProductResponseDto.class)));
        when(productService.listProductsWithFilter(Optional.empty(),Optional.empty())).thenReturn(productList);
        ResponseEntity<RestResponse<List<ProductResponseDto>>> result = productController.listProductsWithFilter(Optional.empty(),Optional.empty());
        assertEquals(200,result.getStatusCodeValue());
    }

    @Test
    void shouldListProductsWithFilter_whenMinPriceParameterFilled() {
        List<ProductResponseDto> productList = new ArrayList<>(List.of(mock(ProductResponseDto.class)));
        lenient().when(productService.listProductsWithFilter(Optional.of((short) 10),Optional.empty())).thenReturn(productList);
        ResponseEntity<RestResponse<List<ProductResponseDto>>> result = productController.listProductsWithFilter(Optional.empty(),Optional.empty());
        assertEquals(200,result.getStatusCodeValue());
    }

    @Test
    void shouldListProductsWithFilter_whenMaxPriceParameterFilled() {
        List<ProductResponseDto> productList = new ArrayList<>(List.of(mock(ProductResponseDto.class)));
        lenient().when(productService.listProductsWithFilter(Optional.empty(),Optional.of((short) 100))).thenReturn(productList);
        ResponseEntity<RestResponse<List<ProductResponseDto>>> result = productController.listProductsWithFilter(Optional.empty(),Optional.empty());
        assertEquals(200,result.getStatusCodeValue());
    }

    @Test
    void shouldListProductsWithFilter_whenAllParameterFilled() {
        List<ProductResponseDto> productList = new ArrayList<>(List.of(mock(ProductResponseDto.class)));
        lenient().when(productService.listProductsWithFilter(Optional.of((short) 5),Optional.of((short) 100))).thenReturn(productList);
        ResponseEntity<RestResponse<List<ProductResponseDto>>> result = productController.listProductsWithFilter(Optional.empty(),Optional.empty());
        assertEquals(200,result.getStatusCodeValue());
    }

    @Test
    void shouldListProductsByProductType() {
        List<ProductResponseDto> productList = new ArrayList<>(List.of(mock(ProductResponseDto.class)));
        lenient().when(productService.findByProductType(any())).thenReturn(productList);
        ResponseEntity<RestResponse<List<ProductResponseDto>>> result = productController.listProductsByProductType(any());
        assertEquals(200,result.getStatusCodeValue());
    }

    @Test
    void shouldListStats() {
        List<ProductTypeStatsDto> listStats = new ArrayList<>(List.of(mock(ProductTypeStatsDto.class)));
        lenient().when(productService.getProductTypeStats(any())).thenReturn(listStats);
        ResponseEntity<RestResponse<List<ProductTypeStatsDto>>> result = productController.listStats(any());
        assertEquals(200,result.getStatusCodeValue());

    }

}

package com.softtech.marketapi.service;

import com.softtech.marketapi.dto.request.ProductDto;
import com.softtech.marketapi.dto.request.ProductTypeStatsDto;
import com.softtech.marketapi.dto.request.ProductUpdateRequestDto;
import com.softtech.marketapi.dto.response.ProductResponseDto;
import com.softtech.marketapi.entity.Product;
import com.softtech.marketapi.service.entityservice.ProductEntityService;
import com.softtech.marketapi.service.entityservice.VatRateEntityService;
import com.softtech.marketapi.util.PriceUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    ProductEntityService productEntityService;
    @Mock
    VatRateEntityService vatRateEntityService;
    @Mock
    PriceUtil priceUtil;

    @InjectMocks
    ProductService productService;

    @Test
    void shouldSaveProduct() {
        Product product = mock(Product.class);
        ProductDto productDto = mock(ProductDto.class);

        String productName = "test";
        product.setProductName(productName);
        BigDecimal priceWithoutVat = new BigDecimal(40);

        when(product.getProductName()).thenReturn(productName);
        when(productDto.getPriceWithoutVat()).thenReturn(priceWithoutVat);
        lenient().when(productEntityService.saveProduct(any())).thenReturn(product);

        ProductResponseDto result = productService.saveProduct(productDto);
        assertEquals(productName,result.getProductName());
    }

    @Test
    void shouldNotSaveProduct_whenParameterNull(){
        assertThrows(NullPointerException.class, () -> productService.saveProduct(null));
    }
    @Test
    void shouldNotSaveProduct_whenParameterNegative(){
        ProductDto productDto = mock(ProductDto.class);
        BigDecimal priceWithoutVat = new BigDecimal(40);
        productDto.setPriceWithoutVat(priceWithoutVat);
        assertThrows(NullPointerException.class, () -> productService.saveProduct(productDto));
    }

    @Test
    void shouldUpdateProduct() {
        Product product = mock(Product.class);
        product.setProductName("test");
        ProductUpdateRequestDto productUpdateRequestDto = mock(ProductUpdateRequestDto.class);
        productUpdateRequestDto.setProductName("test");
        when(productEntityService.findById(any())).thenReturn(product);
        ProductResponseDto result = productService.updateProduct(productUpdateRequestDto);
        assertEquals(product.getProductName(),result.getProductName());
    }

    @Test
    void shouldFindByProductType() {
        Product product = mock(Product.class);
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        when(productEntityService.findByProductType(any())).thenReturn(productList);
        List<ProductResponseDto> result = productService.findByProductType(any());
        assertEquals(1,result.size());
    }

    @Test
    void shouldListProductsWithFilter_whenParametersEmpty() {
        Product product = mock(Product.class);
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        when(productEntityService.findAllProducts()).thenReturn(productList);
        List<ProductResponseDto> result = productService.listProductsWithFilter(Optional.empty(), Optional.empty());
        assertEquals(1,result.size());
    }

    @Test
    void shouldListProductsWithFilter_whenBothParameterIsFilled() {
        Product product = mock(Product.class);
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        when(productEntityService.findByPriceWithVatBetween(any(),any())).thenReturn(productList);
        List<ProductResponseDto> result = productService.listProductsWithFilter(
                Optional.of((short)5),Optional.of((short)150));
        assertEquals(1,result.size());
    }

    @Test
    void shouldListProductsWithFilter_whenMinPriceParameterIsFilled() {
        Product product = mock(Product.class);
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        when(productEntityService.listByPriceGreaterThan(any())).thenReturn(productList);
        List<ProductResponseDto> result = productService.listProductsWithFilter(Optional.of((short)5),Optional.empty());
        assertEquals(1,result.size());
    }

    @Test
    void shouldListProductsWithFilter_whenMaxPriceParameterIsFilled() {
        Product product = mock(Product.class);
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        when(productEntityService.listByPriceLessThan(any())).thenReturn(productList);
        List<ProductResponseDto> result = productService.listProductsWithFilter(Optional.empty(),Optional.of((short)200));
        assertEquals(1,result.size());
    }

    @Test
    void getProductTypeStats() {
        ProductTypeStatsDto productTypeStatsDto = mock(ProductTypeStatsDto.class);
        List<ProductTypeStatsDto> productList = new ArrayList<>();
        productList.add(productTypeStatsDto);
        when(productEntityService.getProductTypeStats(any())).thenReturn(productList);
        List<ProductTypeStatsDto> result = productService.getProductTypeStats(any());
        assertEquals(1,result.size());
    }

}

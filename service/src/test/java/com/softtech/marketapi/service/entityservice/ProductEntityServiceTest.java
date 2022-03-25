package com.softtech.marketapi.service.entityservice;

import com.softtech.marketapi.dto.request.ProductTypeStatsDto;
import com.softtech.marketapi.entity.Product;
import com.softtech.marketapi.entity.User;
import com.softtech.marketapi.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductEntityServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductEntityService productEntityService;

    @Test
    void shouldSaveProduct() {
        Product product = mock(Product.class);

        when(productRepository.save(any())).thenReturn(product);
        Product result = productEntityService.saveProduct(product);
        assertEquals(product,result);

    }

    @Test
    void shouldFindById() {
        UUID uuid = UUID.randomUUID();

        Product product = mock(Product.class);
        product.setId(uuid);

        when(productRepository.findById(uuid)).thenReturn(Optional.of(product));
        Product result = productEntityService.findById(uuid);
        assertEquals(product,result);
    }


    @Test
    void shouldFindAllProducts() {
        Product product = mock(Product.class);
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        when(productRepository.findAll()).thenReturn(productList);

        List<Product> result = productEntityService.findAllProducts();
        assertEquals(productList,result);
    }

    @Test
    void shouldFindByProductType() {
        Product product = mock(Product.class);
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        when(productRepository.findByProductType(any())).thenReturn(productList);
        List<Product> result = productEntityService.findByProductType(any());
        assertEquals(1,result.size());
    }

    @Test
    void shouldFindByPriceWithVatBetween() {
        Product product = mock(Product.class);
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        when(productRepository.findByPriceWithVatBetween(any(),any())).thenReturn(productList);
        List<Product> result = productEntityService.findByPriceWithVatBetween(
                BigDecimal.valueOf(4), BigDecimal.valueOf(100)
        );

        assertEquals(1,result.size());
    }

    @Test
    void shouldListByPriceGreaterThan() {
        Product product = mock(Product.class);
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        when(productRepository.findByPriceWithVatGreaterThan(any())).thenReturn(productList);
        List<Product> result = productEntityService.listByPriceGreaterThan(
                BigDecimal.valueOf(4)
        );

        assertEquals(1,result.size());
    }

    @Test
    void shouldListByPriceLessThan() {
        Product product = mock(Product.class);
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        when(productRepository.findByPriceWithVatLessThan(any())).thenReturn(productList);
        List<Product> result = productEntityService.listByPriceLessThan(
                BigDecimal.valueOf(100)
        );

        assertEquals(1,result.size());
    }

    @Test
    void shouldGetProductTypeStats() {
        ProductTypeStatsDto productTypeStatsDto = mock(ProductTypeStatsDto.class);
        List<ProductTypeStatsDto> productTypeStatsDtoList = new ArrayList<>();
        productTypeStatsDtoList.add(productTypeStatsDto);
        when(productRepository.getProductTypeStats(any())).thenReturn(productTypeStatsDtoList);
        List<ProductTypeStatsDto> result = productEntityService.getProductTypeStats(any());
        assertEquals(1,result.size());
    }
}

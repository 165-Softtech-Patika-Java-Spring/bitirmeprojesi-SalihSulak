package com.softtech.marketapi.service.entityservice;

import com.softtech.marketapi.dto.request.ProductTypeStatsDto;
import com.softtech.marketapi.entity.Product;
import com.softtech.marketapi.enums.ProductType;
import com.softtech.marketapi.enums.errors.ProductErrorMessages;
import com.softtech.marketapi.generic.exceptions.GenericBusinessException;
import com.softtech.marketapi.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductEntityService {
    private final ProductRepository productRepository;

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public Product findById(UUID id){
        return productRepository.findById(id)
            .orElseThrow(() -> new GenericBusinessException(ProductErrorMessages.PRODUCT_NOT_FOUND));}

    public void deleteProduct(Product product){
        productRepository.delete(product);
    }

    public List<Product> findAllProducts(){
        return productRepository.findAll();
    }

    public List<Product> findByProductType(ProductType productType){
        List<Product> productList = productRepository.findByProductType(productType);

        if (productList.isEmpty())
            throw new GenericBusinessException(ProductErrorMessages.PRODUCT_NOT_FOUND);

        return productList;
    }

    public List<Product> findByPriceWithVatBetween(BigDecimal minPrice, BigDecimal maxPrice){
        List<Product> productList = productRepository.findByPriceWithVatBetween(minPrice, maxPrice);
        return productList;
    }

    public List<Product> listByPriceGreaterThan(BigDecimal minPrice){
        List<Product> productList = productRepository.findByPriceWithVatGreaterThan(minPrice);
        return productList;
    }

    public List<Product> listByPriceLessThan(BigDecimal maxPrice){
        List<Product> productList = productRepository.findByPriceWithVatLessThan(maxPrice);
        return productList;
    }
    
    public List<ProductTypeStatsDto> getProductTypeStats(ProductType productType){
        List<ProductTypeStatsDto> productTypeStats = productRepository.getProductTypeStats(productType);
        return productTypeStats;
    }

}

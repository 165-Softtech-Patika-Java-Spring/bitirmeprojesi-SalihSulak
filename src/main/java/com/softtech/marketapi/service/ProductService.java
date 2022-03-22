package com.softtech.marketapi.service;

import com.softtech.marketapi.converter.ProductMapper;
import com.softtech.marketapi.dto.request.ProductTypeStatsDto;
import com.softtech.marketapi.dto.request.ProductDto;
import com.softtech.marketapi.dto.request.ProductUpdateRequestDto;
import com.softtech.marketapi.dto.response.ProductResponseDto;
import com.softtech.marketapi.entity.Product;
import com.softtech.marketapi.enums.ProductType;
import com.softtech.marketapi.enums.errors.ProductErrorMessages;
import com.softtech.marketapi.generic.exceptions.GenericBusinessException;
import com.softtech.marketapi.service.entityservice.ProductEntityService;
import com.softtech.marketapi.service.entityservice.VatRateEntityService;
import com.softtech.marketapi.util.PriceUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductEntityService productEntityService;
    private final VatRateEntityService vatRateEntityService;
    private final PriceUtil priceUtil;

    public ProductResponseDto saveProduct(ProductDto productDto){
        if (productDto.getPriceWithoutVat().compareTo(BigDecimal.ZERO) <= 0)
            throw new GenericBusinessException(ProductErrorMessages.PRODUCT_PRICE_NOT_POSITIVE);

        Product product = ProductMapper.INSTANCE.convertDtoToProduct(productDto);
        setPriceWithVat(product);

        productEntityService.saveProduct(product);
        ProductResponseDto productResponseDto = ProductMapper.INSTANCE.convertToResponseDto(product);
        return productResponseDto;
    }

    public ProductResponseDto updateProduct(ProductUpdateRequestDto updateRequest){
        Product product = productEntityService.findById(updateRequest.getId());

        updateNonNullFieldsInProduct(updateRequest, product);

        setPriceWithVat(product);

        productEntityService.saveProduct(product);

        ProductResponseDto productResponseDto = ProductMapper.INSTANCE.convertToResponseDto(product);
        return productResponseDto;
    }

    public List<ProductResponseDto> findByProductType(ProductType productType){
        List<Product> productList = productEntityService.findByProductType(productType);
        return ProductMapper.INSTANCE.convertToResponseList(productList);
    }

    public List<ProductResponseDto> listProductsWithFilter(Optional<Short> minPrice, Optional<Short> maxPrice) {
        List<Product> productList;

        if (minPrice.isEmpty()&&maxPrice.isEmpty()){
            productList = productEntityService.findAllProducts();
            throwExceptionIfListEmpty(productList);
            return ProductMapper.INSTANCE.convertToResponseList(productList);
        }
        else {
            BigDecimal minPriceBigDecimal;
            BigDecimal maxPriceBigDecimal;

            if (minPrice.isPresent() && maxPrice.isPresent()) {
                minPriceBigDecimal = BigDecimal.valueOf(minPrice.get());
                maxPriceBigDecimal = BigDecimal.valueOf(maxPrice.get());

                productList = productEntityService.findByPriceWithVatBetween(minPriceBigDecimal, maxPriceBigDecimal);
                throwExceptionIfListEmpty(productList);
                return ProductMapper.INSTANCE.convertToResponseList(productList);
            } else if (minPrice.isPresent()) {
                minPriceBigDecimal = BigDecimal.valueOf(minPrice.get());
                productList = productEntityService.listByPriceGreaterThan(minPriceBigDecimal);
                throwExceptionIfListEmpty(productList);
                return ProductMapper.INSTANCE.convertToResponseList(productList);
            } else {
                maxPriceBigDecimal = BigDecimal.valueOf(maxPrice.get());
                productList = productEntityService.listByPriceLessThan(maxPriceBigDecimal);
                throwExceptionIfListEmpty(productList);
                return ProductMapper.INSTANCE.convertToResponseList(productList);
            }
        }
    }

    public List<ProductTypeStatsDto> getProductTypeStats(ProductType productType){
        List<ProductTypeStatsDto> productTypeStats = productEntityService.getProductTypeStats(productType);
        return productTypeStats;
    }


    public UUID deleteProduct(UUID id){
        Product product = findById(id);
        productEntityService.deleteProduct(product);
        return product.getId();
    }


    private Product findById(UUID id){
        return productEntityService.findById(id);
    }

    private void updateNonNullFieldsInProduct(ProductUpdateRequestDto updateRequest, Product product) {
        if (Objects.nonNull(updateRequest.getProductType()))
            product.setProductType(updateRequest.getProductType());
        if (Objects.nonNull(updateRequest.getPriceWithoutVat()))
            product.setPriceWithoutVat(updateRequest.getPriceWithoutVat());
        if (Objects.nonNull(updateRequest.getProductName()))
            product.setProductName(updateRequest.getProductName());
    }

    private short getVatRate(ProductType productType) {
        return vatRateEntityService.findVatRateByProductType(productType);
    }

    private void setPriceWithVat(Product product) {
        ProductType productType = product.getProductType();
        BigDecimal priceWithoutVat = product.getPriceWithoutVat();

        short vatRate = getVatRate(productType);
        BigDecimal priceWithVat = priceUtil.getPriceWithVat(priceWithoutVat, vatRate);

        product.setPriceWithVat(priceWithVat);
    }




    private void throwExceptionIfListEmpty(List<Product> productList) {
        if(productList.isEmpty())
            throw new GenericBusinessException(ProductErrorMessages.PRODUCT_NOT_FOUND);
    }
}

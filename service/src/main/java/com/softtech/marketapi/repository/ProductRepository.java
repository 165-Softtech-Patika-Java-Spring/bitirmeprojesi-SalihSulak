package com.softtech.marketapi.repository;

import com.softtech.marketapi.dto.request.ProductTypeStatsDto;
import com.softtech.marketapi.entity.Product;
import com.softtech.marketapi.enums.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    List<Product> findByProductType(ProductType productType);

    @Query("SELECT p FROM Product p where p.priceWithVat BETWEEN ?1 AND ?2")
    List<Product> findByPriceWithVatBetween(BigDecimal minPrice, BigDecimal maxPrice);

    List<Product> findByPriceWithVatGreaterThan(BigDecimal priceWithVat);

    List<Product> findByPriceWithVatLessThan(BigDecimal priceWithVat);


    @Query(value = "SELECT new com.softtech.marketapi.dto.request.ProductTypeStatsDto(" +
            "min(p.priceWithVat),max(p.priceWithVat),avg(p.priceWithVat),count(p.id), p.productType ,v.vatPercentage) " +
            "FROM Product p " +
            "LEFT JOIN VatRate v " +
            "ON p.productType=v.productType " +
            "WHERE p.productType=?1 " +
            "GROUP BY p.productType,v.vatPercentage")
    List<ProductTypeStatsDto> getProductTypeStats(ProductType productType);

}

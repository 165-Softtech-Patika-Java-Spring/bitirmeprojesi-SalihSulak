package com.softtech.marketapi.repository;

import com.softtech.marketapi.entity.VatRate;
import com.softtech.marketapi.enums.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VatRateRepository extends JpaRepository<VatRate, UUID> {
    @Query("select v.vatPercentage from VatRate v where v.productType=?1")
    Short findVatPercentageByProductType(ProductType productType);

}

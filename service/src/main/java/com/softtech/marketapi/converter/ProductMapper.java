package com.softtech.marketapi.converter;

import com.softtech.marketapi.dto.request.ProductDto;
import com.softtech.marketapi.dto.response.ProductResponseDto;
import com.softtech.marketapi.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product convertDtoToProduct(ProductDto productDto);

    ProductDto convertProductToDto(Product product);

    ProductResponseDto convertToResponseDto(Product product);

    List<ProductResponseDto> convertToResponseList(List<Product> productList);
}

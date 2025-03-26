package com.tracktive.productservice.util.factory.Impl;

import com.tracktive.productservice.model.DTO.ProductDTO;
import com.tracktive.productservice.model.DTO.ProductManagementDTO;
import com.tracktive.productservice.model.Enum.ProductCategory;
import com.tracktive.productservice.util.converter.Impl.ProductManagementTireConverter;
import com.tracktive.productservice.util.converter.ProductManagementConverter;
import com.tracktive.productservice.util.factory.ProductManagementConverterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
* Description: Product Management Converter Factory
* @author William Theo
* @date 25/3/2025
*/
@Component
public class ProductManagementConverterFactoryImpl implements ProductManagementConverterFactory {

    private final Map<ProductCategory, ProductManagementConverter<? extends ProductManagementDTO>> converterMap;

    @Autowired
    public ProductManagementConverterFactoryImpl(List<ProductManagementConverter<? extends ProductManagementDTO>> converters){
        this.converterMap = new HashMap<>();
        for(ProductManagementConverter<? extends ProductManagementDTO> converter: converters){
            if (converter instanceof ProductManagementTireConverter){
                converterMap.put(ProductCategory.TIRE, converter);
            }
        }
    }

    @Override
    public ProductManagementDTO readProduct(ProductDTO productDTO){
        ProductManagementConverter<? extends ProductManagementDTO> converter = converterMap.get(productDTO.getProductCategory());
        if (!Objects.isNull(converter)){
            return converter.read(productDTO);
        }
        return null;
    }

    @Override
    public ProductManagementDTO createProduct(ProductManagementDTO productManagementDTO) {
        ProductManagementConverter<? extends ProductManagementDTO> converter = converterMap.get(productManagementDTO.getProductCategory());
        if (!Objects.isNull(converter)){
            return converter.create(productManagementDTO);
        }
        return null;
    }
}

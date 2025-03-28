package com.tracktive.productservice.util.factory.Impl;

import com.tracktive.productservice.exception.ProductManagementConverterNotFoundException;
import com.tracktive.productservice.model.DTO.ProductDTO;
import com.tracktive.productservice.model.DTO.ProductManagementDTO;
import com.tracktive.productservice.model.DTO.ProductManagementRequestDTO;
import com.tracktive.productservice.model.Enum.ProductCategory;
import com.tracktive.productservice.util.converter.ProductManagementConverter;
import com.tracktive.productservice.util.converter.ProductManagementTireConverter;
import com.tracktive.productservice.util.factory.ProductManagementFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
* Description: Product Management Factory Implementation
* @author William Theo
* @date 28/3/2025
*/
@Component
public class ProductManagementFactoryImpl implements ProductManagementFactory {

    private final Map<ProductCategory, ProductManagementConverter<? extends ProductManagementDTO>> converterMap;


    public ProductManagementFactoryImpl(List<ProductManagementConverter<? extends ProductManagementDTO>> converters){
        this.converterMap = new HashMap<>();
        for (ProductManagementConverter<? extends ProductManagementDTO> converter: converters){
            if (converter instanceof ProductManagementTireConverter){
                converterMap.put(ProductCategory.TIRE, converter);
            }
        }
    }

    @Override
    public ProductManagementDTO selectProduct(ProductDTO productDTO) {
        ProductManagementConverter<? extends ProductManagementDTO> converter = converterMap.get(productDTO.getProductCategory());
        if (!Objects.isNull(converter)){
            return converter.read(productDTO);
        }
        throw new ProductManagementConverterNotFoundException("Product Management Converter with Category " + productDTO.getProductCategory() + " Not Found");
    }

    @Override
    public ProductManagementDTO addProduct(ProductManagementRequestDTO productManagementRequestDTO) {
        ProductManagementConverter<? extends ProductManagementDTO> converter = converterMap.get(productManagementRequestDTO.getProductCategory());
        if (!Objects.isNull(converter)){
            return converter.create(productManagementRequestDTO);
        }
        throw new ProductManagementConverterNotFoundException("Product Management Converter with Category " + productManagementRequestDTO.getProductCategory() + " Not Found");
    }

    @Override
    public ProductManagementDTO updateProduct(ProductManagementDTO productManagementDTO) {
        return null;
    }

    @Override
    public ProductManagementDTO deleteProduct(ProductDTO productDTO) {
        return null;
    }
}

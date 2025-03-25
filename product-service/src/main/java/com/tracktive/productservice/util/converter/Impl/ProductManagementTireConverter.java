package com.tracktive.productservice.util.converter.Impl;

import com.tracktive.productservice.model.DTO.ProductDTO;
import com.tracktive.productservice.model.DTO.ProductManagementTireDTO;
import com.tracktive.productservice.model.DTO.TireDTO;
import com.tracktive.productservice.service.TireService;
import com.tracktive.productservice.util.converter.ProductManagementConverter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
* Description: Product Management Tire Converter
* @author William Theo
* @date 25/3/2025
*/
@Component
public class ProductManagementTireConverter implements ProductManagementConverter<ProductManagementTireDTO> {

    private final TireService tireService;

    @Autowired
    public ProductManagementTireConverter(TireService tireService) {
        this.tireService = tireService;
    }

    @Override
    public ProductManagementTireDTO convert(ProductDTO productDTO) {
        ProductManagementTireDTO productManagementTireDTO = new ProductManagementTireDTO();
        BeanUtils.copyProperties(productDTO, productManagementTireDTO);

        TireDTO tireDTO = tireService.selectTireById(productDTO.getProductId());
        if (!Objects.isNull(tireDTO)){
            productManagementTireDTO.setTireSku(tireDTO.getTireSku());
            productManagementTireDTO.setWidth(tireDTO.getWidth());
            productManagementTireDTO.setAspectRatio(tireDTO.getAspectRatio());
            productManagementTireDTO.setRimDiameter(tireDTO.getRimDiameter());
            productManagementTireDTO.setLoadIndex(tireDTO.getLoadIndex());
            productManagementTireDTO.setSpeedRating(tireDTO.getSpeedRating());
            productManagementTireDTO.setTireSeason(tireDTO.getTireSeason());
            productManagementTireDTO.setTreadPattern(tireDTO.getTreadPattern());
            productManagementTireDTO.setTireType(tireDTO.getTireType());
            productManagementTireDTO.setRunFlat(tireDTO.getRunFlat());
        }
        return productManagementTireDTO;
    }
}

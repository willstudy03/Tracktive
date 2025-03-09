package com.tracktive.productservice.repository.Impl;

import com.tracktive.productservice.model.DAO.TireDAO;
import com.tracktive.productservice.model.DTO.TireDTO;
import com.tracktive.productservice.model.entity.Tire;
import com.tracktive.productservice.repository.TireRepository;
import com.tracktive.productservice.util.TireConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* Description: Tire Repository Implementation
* @author William Theo
* @date 9/3/2025
*/
@Repository
public class TireRepositoryImpl implements TireRepository {

    private final TireDAO tireDAO;

    @Autowired
    public TireRepositoryImpl(TireDAO tireDAO) {
        this.tireDAO = tireDAO;
    }

    @Override
    public List<TireDTO> selectAllTire() {
        return tireDAO.selectAllTires().stream()
                .map(TireConverter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TireDTO> selectTireByParams(TireDTO tireDTO) {
        validateTireDTO(tireDTO);
        Tire param = TireConverter.toEntity(tireDTO);
        return tireDAO.selectTireByParams(param).stream()
                .map(TireConverter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TireDTO> selectTireById(String id) {
        validateId(id);
        return tireDAO.selectTireById(id).map(TireConverter::toDTO);
    }

    @Override
    public Optional<TireDTO> lockTireById(String id) {
        validateId(id);
        return tireDAO.lockTireById(id).map(TireConverter::toDTO);
    }

    @Override
    public Optional<TireDTO> selectTireBySKU(String sku) {
        validateSku(sku);
        return tireDAO.selectTireBySKU(sku).map(TireConverter::toDTO);
    }

    @Override
    public boolean addTire(TireDTO tireDTO) {
        validateTireDTO(tireDTO);
        Tire tire = TireConverter.toEntity(tireDTO);
        return tireDAO.addTire(tire) > 0;
    }

    @Override
    public boolean updateTire(TireDTO tireDTO) {
        validateTireDTO(tireDTO);
        Tire tire = TireConverter.toEntity(tireDTO);
        return tireDAO.updateTire(tire) > 0;
    }

    @Override
    public boolean deleteById(String id) {
        validateId(id);
        return tireDAO.deleteTireById(id) >0;
    }

    private void validateId(String id){
        if (Objects.isNull(id) || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Tire ID cannot be null or empty");
        }
    }

    private void validateSku(String sku){
        if (Objects.isNull(sku) || sku.trim().isEmpty()) {
            throw new IllegalArgumentException("Tire SKU cannot be null or empty");
        }
    }

    private void validateTireDTO(TireDTO tireDTO) {
        if (Objects.isNull(tireDTO)) {
            throw new IllegalArgumentException("TireDTO cannot be null");
        }
    }
}

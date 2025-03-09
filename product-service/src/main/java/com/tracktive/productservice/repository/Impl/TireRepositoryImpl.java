package com.tracktive.productservice.repository.Impl;

import com.tracktive.productservice.model.DAO.TireDAO;
import com.tracktive.productservice.model.entity.Tire;
import com.tracktive.productservice.repository.TireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    public List<Tire> selectAllTire() {
        return tireDAO.selectAllTires();
    }

    @Override
    public List<Tire> selectTireByParams(Tire tire) {
        validateTireDTO(tire);
        return tireDAO.selectTireByParams(tire);
    }

    @Override
    public Optional<Tire> selectTireById(String id) {
        validateId(id);
        return tireDAO.selectTireById(id);
    }

    @Override
    public Optional<Tire> lockTireById(String id) {
        validateId(id);
        return tireDAO.selectTireById(id);
    }

    @Override
    public Optional<Tire> selectTireBySKU(String sku) {
        validateSku(sku);
        return tireDAO.selectTireBySKU(sku);
    }

    @Override
    public boolean addTire(Tire tire) {
        validateTireDTO(tire);
        return tireDAO.addTire(tire) > 0;
    }

    @Override
    public boolean updateTire(Tire tire) {
        validateTireDTO(tire);
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

    private void validateTireDTO(Tire tire) {
        if (Objects.isNull(tire)) {
            throw new IllegalArgumentException("TireDTO cannot be null");
        }
    }
}

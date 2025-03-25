package com.tracktive.productservice.repository.Impl;

import com.tracktive.productservice.exception.DatabaseOperationException;
import com.tracktive.productservice.exception.ForeignKeyConstraintException;
import com.tracktive.productservice.exception.ProductAlreadyExistsException;
import com.tracktive.productservice.model.DAO.TireDAO;
import com.tracktive.productservice.model.DTO.TireDTO;
import com.tracktive.productservice.model.entity.Tire;
import com.tracktive.productservice.repository.TireRepository;
import com.tracktive.productservice.util.TireConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
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
        Tire param = TireConverter.toEntity(tireDTO);
        return tireDAO.selectTireByParams(param).stream()
                .map(TireConverter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TireDTO> selectTireById(String id) {
        return tireDAO.selectTireById(id).map(TireConverter::toDTO);
    }

    @Override
    public Optional<TireDTO> lockTireById(String id) {
        return tireDAO.lockTireById(id).map(TireConverter::toDTO);
    }

    @Override
    public Optional<TireDTO> selectTireBySKU(String sku) {
        return tireDAO.selectTireBySKU(sku).map(TireConverter::toDTO);
    }

    @Override
    public boolean addTire(TireDTO tireDTO) {
        try {
            Tire tire = TireConverter.toEntity(tireDTO);
            return tireDAO.addTire(tire) > 0;
        } catch (DuplicateKeyException e) {
            throw new ProductAlreadyExistsException("Tire with id " + tireDTO.getId() + "or same sku " + tireDTO.getTireSku() + " already exists", e);
        } catch (DataIntegrityViolationException e) {
            Throwable rootCause = e.getRootCause(); // Get the root cause
            if (rootCause instanceof SQLIntegrityConstraintViolationException) {
                throw new ForeignKeyConstraintException("Product with ID " + tireDTO.getId() + " does not exist.", e);
            }
            throw new DatabaseOperationException("Database integrity constraint violation occurred.", e);
        } catch (DataAccessException e) {
            throw new DatabaseOperationException("Failed to add tire to the database", e);
        }
    }

    @Override
    public boolean updateTire(TireDTO tireDTO) {
        try {
            Tire tire = TireConverter.toEntity(tireDTO);
            return tireDAO.updateTire(tire) > 0;
        } catch (DuplicateKeyException e) {
            throw new ProductAlreadyExistsException("A tire with the same SKU already exists", e);
        } catch (DataAccessException e) {
            throw new DatabaseOperationException("Failed to update tire in the database", e);
        }
    }

    @Override
    public boolean deleteById(String id) {
        return tireDAO.deleteTireById(id) > 0;
    }
}

package com.tracktive.productservice.model.DAO;


import com.tracktive.productservice.model.entity.Tire;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

/**
* Description: Tire DAO for Mybatis mapping
* @author William Theo
* @date 8/3/2025
*/
@Mapper
public interface TireDAO {
    // Read Operations
    List<Tire> selectAllTires();
    List<Tire> selectTireByParams(Tire tire);
    Optional<Tire> selectTireById(String id);
    Optional<Tire> selectTireBySKU(String sku);

    // Lock operation
    Optional<Tire> lockTireById(String id);

    // Insert operation
    int addTire(Tire tire);

    // Update operation
    int updateTire(Tire tire);

    // Delete operation
    int deleteTireById(String id);
}

package com.tracktive.userservice.model.DAO;

import com.tracktive.userservice.model.entity.Courier;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

/**
* Description: Courier DAO for mybatis mapping
* @author William Theo
* @date 2/3/2025
*/
@Mapper
public interface CourierDAO {

    // Select operations
    List<Courier> selectAllCouriers();
    Optional<Courier> selectCourierById(String id);
    // Lock operation
    Optional<Courier> lockCourierById(String id);
    // Insert operation
    int addCourier(Courier courier);
    // Update operation
    int updateCourier(Courier courier);
    // Delete operation
    int deleteCourierById(String id);
}

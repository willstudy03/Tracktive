package com.tracktive.userservice.model.DAO;

import com.tracktive.userservice.model.entity.Retailer;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Optional;

/**
* Description: Retailer DAO for mybatis mapping
* @author William Theo
* @date 1/3/2025
*/
@Mapper
public interface RetailerDAO {

    // Select operations
    List<Retailer> selectAllRetailers();
    Optional<Retailer> selectRetailerById(String id);
    // Lock operation
    Optional<Retailer> lockRetailerById(String id);
    // Insert operation
    int addRetailer(Retailer retailer);
    // Update operation
    int updateRetailer(Retailer retailer);
    // Delete operation
    int deleteRetailerById(String id);
}

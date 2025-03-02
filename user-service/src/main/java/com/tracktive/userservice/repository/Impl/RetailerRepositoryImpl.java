package com.tracktive.userservice.repository.Impl;

import com.tracktive.userservice.model.DAO.RetailerDAO;
import com.tracktive.userservice.model.entity.Retailer;
import com.tracktive.userservice.repository.RetailerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
* Description: Retailer Repository Implementation
* @author William Theo
* @date 2/3/2025
*/
@Repository
public class RetailerRepositoryImpl implements RetailerRepository {

    private final RetailerDAO retailerDAO;

    @Autowired
    RetailerRepositoryImpl(RetailerDAO retailerDAO){
        this.retailerDAO = retailerDAO;
    }

    @Override
    public List<Retailer> selectAllRetailers() {
        return Optional.ofNullable(retailerDAO.selectAllRetailers()).orElse(Collections.emptyList());
    }

    @Override
    public Optional<Retailer> selectRetailerById(String id) {
        return retailerDAO.selectRetailerById(id);
    }

    @Override
    public Optional<Retailer> lockRetailerById(String id) {
        return retailerDAO.lockRetailerById(id);
    }

    @Override
    public boolean addRetailer(Retailer retailer) {
        return retailerDAO.addRetailer(retailer) > 0;
    }

    @Override
    public boolean updateRetailer(Retailer retailer) {
        return retailerDAO.updateRetailer(retailer) > 0;
    }

    @Override
    public boolean deleteById(String id) {
        return retailerDAO.deleteRetailerById(id) > 0;
    }
}

package com.tracktive.userservice.service;

import com.tracktive.userservice.model.DTO.RetailerDTO;
import java.util.List;

/**
 * Description: Retailer CRUD Service Interface
 * @author William Theo
 * @date 5/3/2025
 */
public interface RetailerService {
    List<RetailerDTO> selectAllRetailers();
    RetailerDTO selectRetailerById(String id);
    RetailerDTO lockRetailerById(String id);
    RetailerDTO addRetailer(RetailerDTO retailerDTO);
    RetailerDTO updateRetailer(RetailerDTO retailerDTO);
    void deleteRetailerById(String id);
}

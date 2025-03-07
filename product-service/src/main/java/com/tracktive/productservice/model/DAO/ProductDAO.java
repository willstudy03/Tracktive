package com.tracktive.productservice.model.DAO;

import com.tracktive.productservice.model.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

/**
* Description: Product DAO for mybatis mapping
* @author William Theo
* @date 7/3/2025
*/
@Mapper
public interface ProductDAO {
    // Select operations
    List<Product> selectAllProducts();
    Optional<Product> selectProductById(String id);
    // Lock operation
    Optional<Product> lockProductById(String id);
    // Insert operation
    int addProduct(Product product);
    // Update operation
    int updateProduct(Product product);
    // Delete operation
    int deleteProductById(String id);
}

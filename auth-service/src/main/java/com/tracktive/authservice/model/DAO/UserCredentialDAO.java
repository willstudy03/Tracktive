package com.tracktive.authservice.model.DAO;

import com.tracktive.authservice.model.entity.UserCredential;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

/**
* Description: User Credential DAO for mybatis mapping
* @author William Theo
* @date 23/4/2025
*/
@Mapper
public interface UserCredentialDAO {
    // Select operations
    List<UserCredential> selectAll();
    Optional<UserCredential> selectById(String id);
    Optional<UserCredential> selectByEmail(String email);
    // Lock Operation
    Optional<UserCredential> lockById(String id);
    // Insert operation
    int addUserCredential(UserCredential userCredential);
    // Update operation
    int updateUserCredential(UserCredential userCredential);
    // Delete operation
    int deleteById(String id);
}

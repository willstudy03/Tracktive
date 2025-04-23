package com.tracktive.userservice.model.DAO;

import com.tracktive.userservice.model.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

/**
* Description: User DAO for mybatis mapping
* @author William Theo
* @date 1/3/2025
*/
@Mapper
public interface UserDAO {
    // Select operations
    List<User> selectAllUsers();
    Optional<User> selectUserById(String id);
    // Lock Operation
    Optional<User> lockUserById(String id);
    // Insert operation
    int addUser(User user);
    // Update operation
    int updateUser(User user);
    // Delete operation
    int deleteUserById(String id);
}

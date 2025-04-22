package com.tracktive.userservice.repository.Impl;

import com.tracktive.userservice.exception.DatabaseOperationException;
import com.tracktive.userservice.exception.UserAlreadyExistsException;
import com.tracktive.userservice.model.DAO.UserDAO;
import com.tracktive.userservice.model.DTO.UserDTO;
import com.tracktive.userservice.model.entity.User;
import com.tracktive.userservice.repository.UserRepository;
import com.tracktive.userservice.util.converter.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* Description: User Repository Implementation
* @author William Theo
* @date 1/3/2025
*/
@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserDAO userDAO;

    @Autowired
    public UserRepositoryImpl(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    @Override
    public List<UserDTO> selectAllUsers() {
        return Optional.ofNullable(userDAO.selectAllUsers())
                .orElse(Collections.emptyList())
                .stream()
                .map(UserConverter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO> selectUserById(String id) {
        return userDAO.selectUserById(id).map(UserConverter::toDTO);
    }

    @Override
    public Optional<UserDTO> lockUserById(String id) {
        return userDAO.lockUserById(id).map(UserConverter::toDTO);
    }

    @Override
    public boolean addUser(UserDTO userDTO) {
        try {
            User user = UserConverter.toEntity(userDTO);
            return userDAO.addUser(user) > 0;
        } catch (DuplicateKeyException e) {
            throw new UserAlreadyExistsException("User with id " + userDTO.getId() + " already exists", e);
        } catch (DataAccessException e) {
            throw new DatabaseOperationException("Failed to add product to the database", e);
        }
    }

    @Override
    public boolean updateUser(UserDTO userDTO) {
        User user = UserConverter.toEntity(userDTO);
        return userDAO.updateUser(user) > 0;
    }

    @Override
    public boolean deleteById(String id) {
        return userDAO.deleteUserById(id) > 0;
    }

}

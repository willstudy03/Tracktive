package com.tracktive.authservice.repository.Impl;

import com.tracktive.authservice.exception.DatabaseOperationException;
import com.tracktive.authservice.exception.UserCredentialAlreadyExistsException;
import com.tracktive.authservice.model.DAO.UserCredentialDAO;
import com.tracktive.authservice.model.DTO.UserCredentialDTO;
import com.tracktive.authservice.model.entity.UserCredential;
import com.tracktive.authservice.repository.UserCredentialsRepository;
import com.tracktive.authservice.util.UserCredentialConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
* Description: User Credentials Repository Implementation
* @author William Theo
* @date 23/4/2025
*/
@Repository
public class UserCredentialsRepositoryImpl implements UserCredentialsRepository {

    private final UserCredentialDAO userCredentialDAO;

    @Autowired
    public UserCredentialsRepositoryImpl(UserCredentialDAO userCredentialDAO) {
        this.userCredentialDAO = userCredentialDAO;
    }

    @Override
    public List<UserCredentialDTO> selectAll() {
        return Optional.ofNullable(userCredentialDAO.selectAll())
                .orElse(Collections.emptyList())
                .stream()
                .map(UserCredentialConverter::toDTO)
                .toList();
    }

    @Override
    public Optional<UserCredentialDTO> selectById(String id) {
        return userCredentialDAO.selectById(id).map(UserCredentialConverter::toDTO);
    }

    @Override
    public Optional<UserCredentialDTO> lockById(String id) {
        return userCredentialDAO.lockById(id).map(UserCredentialConverter::toDTO);
    }

    @Override
    public boolean addUserCredential(UserCredentialDTO userCredentialDTO) {
        try {
            UserCredential userCredential = UserCredentialConverter.toEntity(userCredentialDTO);
            return userCredentialDAO.addUserCredential(userCredential) > 0;
        } catch (DuplicateKeyException e) {
            throw new UserCredentialAlreadyExistsException("User Credential with id " + userCredentialDTO.getUserId() + " already exists", e);
        } catch (DataAccessException e) {
            throw new DatabaseOperationException("Failed to add user credential to the database", e);
        }
    }

    @Override
    public boolean updateUserCredential(UserCredentialDTO userCredentialDTO) {
        UserCredential userCredential = UserCredentialConverter.toEntity(userCredentialDTO);
        return userCredentialDAO.updateUserCredential(userCredential) > 0;
    }

    @Override
    public boolean deleteById(String id) {
        return userCredentialDAO.deleteById(id) > 0;
    }
}

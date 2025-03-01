package com.tracktive.userservice.repository.Impl;

import com.tracktive.userservice.model.Enum.UserRole;
import com.tracktive.userservice.model.entity.User;
import com.tracktive.userservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

/**
* Description: UserRepository CRUD Test Case
* @author William Theo
* @date 1/3/2025
*/
@SpringBootTest
class UserRepositoryImplTest {
    @Autowired
    private UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserRepositoryImplTest.class);

    @Test
    public void testAddUser(){
        User user = new User();
        user.setId("23468c87-1674-4448-89e9-1fe00286ab4d");
        user.setName("John Doe");
        user.setEmail("JohnDoe@gmail.com");
        user.setPhoneNumber("012349879");
        user.setUserRole(UserRole.ADMIN);

        boolean isAdded = userRepository.addUser(user);
        assertTrue(isAdded);
    }

    @Test
    public void testSelectAllUsers(){
        List<User> users = userRepository.selectAllUsers();
        logger.info("Users:{}", users);
        assertEquals(users.isEmpty(),false);
    }

    @Test
    public void testSelectUserById(){
        String id = "9a5d60a1-0d3f-432f-98c3-0816b787c7af";
        Optional<User> user = userRepository.selectUserById(id);
        User result = user.get();
        assertEquals(id, result.getId());
    }

    @Test
    public void testUpdateUser(){
        Optional<User> user = userRepository.selectUserById("9a5d60a1-0d3f-432f-98c3-0816b787c7af");
        User updateUser = user.get();
        logger.info("User Name:{}", updateUser.getName());
        updateUser.setName("William Theo");
        assertTrue(userRepository.update(user.get()));
    };

    @Test
    public void testDeleteUser(){
        String id = "23468c87-1674-4448-89e9-1fe00286ab4d";
        boolean delResult = userRepository.deleteById(id);
        Optional<User> user = userRepository.selectUserById(id);
        assertEquals(user, Optional.empty());
        assertTrue(delResult);
    }


}
package com.hashedin.migration.services;

import com.hashedin.migration.entity.User;
import com.hashedin.migration.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void getAllUsersTest() {
        User user = new User();
        user.setId(1L);
        user.setUsername("test");
        user.setPassword("password");
        when(userRepository.findAll()).thenReturn(Arrays.asList(user));
        List<User> result = userService.getAllUsers();
        assertEquals(1, result.size());
    }

    @Test
    public void saveUserTest() {
        User user = new User();
        user.setId(1L);
        user.setUsername("test");
        user.setPassword("password");
        user.setEmail("test@test.com");
        when(userRepository.save(user)).thenReturn(user);
        User savedUser = userService.saveUser(user);
        assertNotNull(savedUser);
        assertEquals(user.getEmail(), savedUser.getEmail());
    }

    @Test
    public void editUserTest() {
        User user = new User();
        user.setId(1L);
        user.setUsername("test");
        user.setPassword("password");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);
        User updatedUser = userService.editUser(1L, user);
        assertNotNull(updatedUser);
        assertEquals(user.getUsername(), updatedUser.getUsername());
    }

    @Test
    public void deleteUserTest() {
        User user = new User();
        user.setId(1L);
        user.setUsername("test");
        user.setPassword("password");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        userService.deleteUser(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        User deletedUser = userRepository.findById(1L).orElse(null);
        assertNull(deletedUser);
    }
}
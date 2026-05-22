package com.guvi.inventorymanager.service;

import com.guvi.inventorymanager.model.User;
import com.guvi.inventorymanager.repo.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthService authService;

    @Test
    void testFindUserByEmail() {

        User user = new User();

        user.setEmail("admin@gmail.com");

        when(userRepository.findByEmailIgnoreCase("admin@gmail.com"))
                .thenReturn(Optional.of(user));
        Optional<User> result =
                userRepository.findByEmailIgnoreCase("admin@gmail.com");

        assertTrue(result.isPresent());
    }
}

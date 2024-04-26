package com.SWE_photoshoot_booking.services;

import com.SWE_photoshoot_booking.domain.entities.Role;
import com.SWE_photoshoot_booking.domain.entities.UserEntity;
import com.SWE_photoshoot_booking.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AuthenticationServiceTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthenticationService authenticationService;

    @Test
    public void testCustomerRegistration() {
        String email = "testCustomer@test.com";
        String password = "testPassword";
        String encodedPassword = "encodedTestPassword";

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);
        userEntity.setPassword(password);
        userEntity.setRole(Role.CUSTOMER);

        when(passwordEncoder.encode(password)).thenReturn(encodedPassword);

        authenticationService.registerUser(userEntity);

        verify(passwordEncoder, times(1)).encode(password);
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    public void testPhotographerRegistration() {
        String email = "testPhotographer@test.com";
        String password = "testPassword";
        String encodedPassword = "encodedTestPassword";

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);
        userEntity.setPassword(password);
        userEntity.setRole(Role.PHOTOGRAPHER);

        when(passwordEncoder.encode(password)).thenReturn(encodedPassword);

        authenticationService.registerUser(userEntity);

        verify(passwordEncoder, times(1)).encode(password);
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }


    @Test
    public void testCustomerLogin() {
        String username = "testCustomer";
        String password = "testPassword";

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(username);
        userEntity.setPassword(password);
        userEntity.setRole(Role.CUSTOMER);

        when(userRepository.findByEmail(username)).thenReturn(Optional.of(userEntity));
        when(passwordEncoder.matches(password, userEntity.getPassword())).thenReturn(true);

        UserDetails result = authenticationService.loadUserByUsername(username);

        assertEquals(username, result.getUsername());
    }

    @Test
    public void testPhotographerLogin() {
        String username = "testPhotographer";
        String password = "testPassword";

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(username);
        userEntity.setPassword(password);
        userEntity.setRole(Role.PHOTOGRAPHER);

        when(userRepository.findByEmail(username)).thenReturn(Optional.of(userEntity));
        when(passwordEncoder.matches(password, userEntity.getPassword())).thenReturn(true);

        UserDetails result = authenticationService.loadUserByUsername(username);

        assertEquals(username, result.getUsername());
    }
}
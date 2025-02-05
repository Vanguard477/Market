package com.website.market;


import com.website.market.controller.AuthController;
import com.website.market.dto.SignInRequest;
import com.website.market.dto.SignUpRequest;
import com.website.market.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class AuthControllerTest extends AbstractAppTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthController authController;


    @Test
    @DisplayName("Регистрация пользователя")
    public void singUp() {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setUsername("admin1");
        signUpRequest.setPassword("admin1");
        signUpRequest.setEmail("admin@admin.com");
        var result = authController.signUp(signUpRequest);
        assertNotNull(result);
        assertNotNull(userRepository.findByUsername("admin1"));
        assertEquals(1, userRepository.count());
    }

    @Test
    @DisplayName("Регистрация пользователя, с существующем именем в БД")
    public void singUpWithExistingUsername() {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setUsername("admin1");
        signUpRequest.setPassword("admin1");
        signUpRequest.setEmail("admin@admin.com");
        authController.signUp(signUpRequest);
        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> authController.signUp(signUpRequest));
        assertEquals("Пользователь с таким именем уже существует", thrown.getMessage());
    }

    @Test
    @DisplayName("Регистрация пользователя, с существующей почтой в БД")
    public void singUpWithExistingEmail() {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setUsername("admin1");
        signUpRequest.setPassword("admin1");
        signUpRequest.setEmail("admin@admin.com");
        authController.signUp(signUpRequest);
        SignUpRequest signUpRequest2 = new SignUpRequest();
        signUpRequest2.setUsername("admin2");
        signUpRequest2.setPassword("admin2");
        signUpRequest2.setEmail("admin@admin.com");
        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> authController.signUp(signUpRequest2));
        assertEquals("Пользователь с таким email уже существует", thrown.getMessage());
    }

    @Test
    @DisplayName("Авторизация пользователя")
    public void singIn() {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setUsername("admin1");
        signUpRequest.setPassword("admin1");
        signUpRequest.setEmail("admin@admin.com");
        authController.signUp(signUpRequest);
        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setUsername("admin1");
        signInRequest.setPassword("admin1");
        var result = authController.signIn(signInRequest);
        assertNotNull(result);
        assertNotNull(userRepository.findByUsername("admin1"));
        assertEquals(1, userRepository.count());
    }

    @Test
    @DisplayName("Авторизация пользователя, введя не верное имя пользователя")
    public void singInInvalidUsername() {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setUsername("admin1");
        signUpRequest.setPassword("admin1");
        signUpRequest.setEmail("admin@admin.com");
        authController.signUp(signUpRequest);
        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setUsername("admin33");
        signInRequest.setPassword("admin33");
        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> authController.signIn(signInRequest));
        assertEquals("Bad credentials", thrown.getMessage());
    }

    @Test
    @DisplayName("Авторизация пользователя, введя не верный пароль пользователя")
    public void singInInvalidPassword() {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setUsername("admin1");
        signUpRequest.setPassword("admin1");
        signUpRequest.setEmail("admin@admin.com");
        authController.signUp(signUpRequest);
        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setUsername("admin1");
        signInRequest.setPassword("admin33");
        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> authController.signIn(signInRequest));
        assertEquals("Bad credentials", thrown.getMessage());
    }



}

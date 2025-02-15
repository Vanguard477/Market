package com.website.market;


import com.website.market.dto.SignInRequest;
import com.website.market.dto.SignUpRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class AuthControllerTest extends AbstractAppTest {

    private SignUpRequest getSignUpRequest() {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setUsername("admin1");
        signUpRequest.setPassword("admin1");
        signUpRequest.setEmail("admin@admin.com");
        return signUpRequest;
    }


    @Test
    @DisplayName("Регистрация пользователя")
    public void signUp() {
        var result = authController.signUp(getSignUpRequest());

        assertNotNull(result);
        assertNotNull(userRepository.findByUsername("admin1"));
        assertEquals(1, userRepository.count());
    }

    @Test
    @DisplayName("Регистрация пользователя, с существующем именем в БД")
    public void singUpWithExistingUsername() {
        authController.signUp(getSignUpRequest());
        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> authController.signUp(getSignUpRequest()));

        assertEquals("Пользователь с таким именем уже существует", thrown.getMessage());
    }

    @Test
    @DisplayName("Регистрация пользователя, с существующей почтой в БД")
    public void singUpWithExistingEmail() {
        SignUpRequest signUpRequest2 = new SignUpRequest();
        signUpRequest2.setUsername("admin2");
        signUpRequest2.setPassword("admin2");
        signUpRequest2.setEmail("admin@admin.com");

        authController.signUp(getSignUpRequest());
        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> authController.signUp(signUpRequest2));

        assertEquals("Пользователь с таким email уже существует", thrown.getMessage());
    }

    @Test
    @DisplayName("Авторизация пользователя")
    public void singIn() {
        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setUsername("admin1");
        signInRequest.setPassword("admin1");

        authController.signUp(getSignUpRequest());
        var result = authController.signIn(signInRequest);

        assertNotNull(result);
        assertNotNull(userRepository.findByUsername("admin1"));
        assertEquals(1, userRepository.count());
    }

    @Test
    @DisplayName("Авторизация пользователя, введя неверное имя пользователя")
    public void singInInvalidUsername() {
        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setUsername("admin33");
        signInRequest.setPassword("admin33");

        authController.signUp(getSignUpRequest());
        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> authController.signIn(signInRequest));

        assertEquals("Bad credentials", thrown.getMessage());
    }

    @Test
    @DisplayName("Авторизация пользователя, введя неверный пароль пользователя")
    public void singInInvalidPassword() {
        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setUsername("admin1");
        signInRequest.setPassword("admin33");

        authController.signUp(getSignUpRequest());
        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> authController.signIn(signInRequest));

        assertEquals("Bad credentials", thrown.getMessage());
    }


}

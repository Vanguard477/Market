package com.website.market;

import com.website.market.controller.CartController;
import com.website.market.entities.Cart;
import com.website.market.entities.User;
import com.website.market.repository.UserRepository;
import com.website.market.service.CurrentUserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@DisplayName("Получение корзины")
public class CartControllerTests extends AbstractAppTest {
    @Autowired
    private CartController cartController;
    @MockBean
    private CurrentUserService currentUserService;
    @Autowired
    private UserRepository userRepository;


    @Test
    @DisplayName("Получение пустой корзины пользователя")
    public void getCartOfUserWithNoItems() {
        User user = new User();
        user.setUsername("admin1");
        user.setPassword("admin1");
        user.setEmail("admin1@gmail.com");
        user.setCart(new Cart());
        userRepository.save(user);
        Mockito.when(currentUserService.getCurrentUserName()).thenReturn(user.getUsername());
        var result = cartController.userCart();
        assertNotNull(result);
        assertEquals(0, result.getItems().size());
    }

}

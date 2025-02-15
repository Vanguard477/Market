package com.website.market;

import com.website.market.controller.AuthController;
import com.website.market.controller.CartController;
import com.website.market.controller.CatalogController;
import com.website.market.repository.CartRepository;
import com.website.market.repository.ItemRepository;
import com.website.market.repository.UserRepository;
import com.website.market.service.CurrentUserService;
import com.website.market.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
abstract class AbstractAppTest {
    @Autowired
    protected ItemRepository itemRepository;
    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected CartRepository cartRepository;
    @Autowired
    protected AuthController authController;
    @Autowired
    protected CartController cartController;
    @Autowired
    protected CatalogController catalogController;
    @MockBean
    protected CurrentUserService currentUserService;
    @MockBean
    protected ItemService itemService;

    @BeforeEach
    @DisplayName("Чистка базы")
    public void clearDb() {
        itemRepository.deleteAll();
        userRepository.deleteAll();
        cartRepository.deleteAll();
    }


}

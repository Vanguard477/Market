package com.website.market;

import com.website.market.dto.FilterDto;
import com.website.market.repository.CartRepository;
import com.website.market.repository.ItemRepository;
import com.website.market.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
abstract class AbstractAppTest {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;

    @BeforeEach
    @DisplayName("Чистка базы")
    public void clearDb() {
        itemRepository.deleteAll();
        userRepository.deleteAll();
        cartRepository.deleteAll();
    }


}

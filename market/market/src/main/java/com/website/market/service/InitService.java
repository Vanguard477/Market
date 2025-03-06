package com.website.market.service;

import com.website.market.dto.SignUpRequest;
import com.website.market.entities.CartItem;
import com.website.market.entities.Item;
import com.website.market.repository.ItemRepository;
import com.website.market.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class InitService {
    private final AuthenticationService authenticationService;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    @Transactional
    public void creatTestUser() {
        SignUpRequest signUpRequest = new SignUpRequest()
                .setUsername("usernameTest")
                .setPassword("passwordTest")
                .setEmail("email@email.com");
        Item item = new Item()
                .setName("Name")
                .setDescription("Description")
                .setPrice(new BigDecimal(1500))
                .setImageUrl("url");
        itemRepository.save(item);
        CartItem cartItem = new CartItem()
                .setQuantity(1)
                .setItem(item);

        log.info("token: {}", authenticationService.signUp(signUpRequest));
        var user = userRepository.findUserWithCartByUsername(signUpRequest.getUsername()).orElseThrow();
        user.getCart().getCartItems().add(cartItem);
        userRepository.save(user);
        log.info("userCart: {}", user.getCart());
    }

}

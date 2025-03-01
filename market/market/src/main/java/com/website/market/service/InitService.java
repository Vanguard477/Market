package com.website.market.service;

import com.website.market.dto.SignUpRequest;
import com.website.market.entities.CartItem;
import com.website.market.entities.Item;
import com.website.market.repository.CartRepository;
import com.website.market.repository.ItemRepository;
import com.website.market.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class InitService {
    private final AuthenticationService authenticationService;
    private final ItemRepository itemRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;

    @PostConstruct
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
        var user = userService.getByUsername(signUpRequest.getUsername());
        var cart = userRepository.findUserWithCartById(user.getId()).orElseThrow().getCart();
        cart.setCartItems(List.of(cartItem));
        cartRepository.save(cart);
        log.info("userCart: {}", user.getCart());
    }

}

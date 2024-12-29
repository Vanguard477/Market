package com.website.market.service;


import com.website.market.entities.Cart;
import com.website.market.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {
    private CartRepository cartRepository;
    private UserService userService;

    public Cart getCart() {
        Cart cart = new Cart();
        return cartRepository.save(cart);
    }



}

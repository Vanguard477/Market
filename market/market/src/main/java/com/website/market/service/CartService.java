package com.website.market.service;


import com.website.market.entities.models.Cart;
import com.website.market.repository.CartRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private CartRepository cartRepository;


    public Cart getItemToCart() {
        Cart cart = new Cart();
        return cartRepository.save(cart);
    }

    public Cart removeItemInCart(String id) {
        Cart cart = cartRepository.findById(id).orElseThrow();
        cartRepository.delete(cart);
        return cart;
    }

    public Cart getCart(String id) {
        Optional<Cart> cart = cartRepository.findById(id);
        return cart.orElseThrow(() -> new EntityNotFoundException("Items not found in basket"));
    }

}

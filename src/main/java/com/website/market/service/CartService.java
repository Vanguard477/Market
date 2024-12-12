package com.website.market.service;


import com.website.market.models.Cart;
import com.website.market.repository.CartRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private CartRepository cartRepository;


    public Cart getItemToCart(String name, String description, String imageUrl, BigDecimal price) {
        Cart cart = new Cart(name, description, price, imageUrl);
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

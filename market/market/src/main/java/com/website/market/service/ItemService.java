package com.website.market.service;

import com.website.market.entities.CartItem;
import com.website.market.entities.Item;
import com.website.market.repository.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class ItemService{
    private ItemRepository itemRepository;
    private UserService userService;

    public void addItemToUserCart(String id) {
        var currentUserCart = userService.getCurrentUser().getCart();
        currentUserCart.getCartItem()
                .add(addItemToCart(id));
    }

    public CartItem addItemToCart(String id) {
        CartItem cartItem = new CartItem();
        cartItem.setItem(getItem(id));
        return cartItem;
    }

    public Item getItem(String id) {
        return itemRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }









}


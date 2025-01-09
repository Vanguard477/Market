package com.website.market.service;


import com.website.market.entities.Cart;
import com.website.market.entities.CartItem;
import com.website.market.entities.Item;
import com.website.market.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {
    private CartRepository cartRepository;
    private UserService userService;

    public Cart addItemTotCurrentUserCart(Item item) {
        var currentUserCart = userService.getCurrentUser().getCart();
        currentUserCart.getCartItems()
                .add(getCartItem(item));
        return cartRepository.save(currentUserCart);
    }

    private CartItem getCartItem(Item item) {
        CartItem cartItem = new CartItem();
        cartItem.setItem(item);
        return cartItem;
    }


}

package com.website.market.mapper;

import com.website.market.dto.CartDto;
import com.website.market.dto.CartItemDto;
import com.website.market.entities.Cart;
import com.website.market.entities.CartItem;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartMapper {

    public static CartDto toCartDto(Cart cart) {
        var itemDto = cart.getCartItems()
                .stream()
                .map(CartMapper::toCartItemDto)
                .toList();
        return new CartDto()
                .setItems(itemDto);


    }

    private static CartItemDto toCartItemDto(CartItem cartItem) {
        var item = cartItem.getItem();
        return new CartItemDto()
                .setId(item.getId())
                .setName(item.getName())
                .setPrice(item.getPrice())
                .setDescription(item.getDescription())
                .setQuantity(cartItem.getQuantity())
                .setImageUrl(item.getImageUrl());


    }


}

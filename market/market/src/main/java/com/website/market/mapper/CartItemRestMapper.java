package com.website.market.mapper;

import com.website.market.dto.ItemRestDto;
import com.website.market.dto.OrderRestDto;
import com.website.market.entities.Cart;
import com.website.market.entities.CartItem;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartItemRestMapper {

    public static OrderRestDto toOrderRestDto(Cart cart, String address, String username) {
        var itemDtos = cart.getCartItems()
                .stream()
                .map(CartItemRestMapper::toUserCartItemDto)
                .toList();
        return new OrderRestDto()
                .setItems(itemDtos)
                .setAddress(address)
                .setUsername(username);
    }

    private static ItemRestDto toUserCartItemDto(CartItem cartItem) {
        var item = cartItem.getItem();
        return new ItemRestDto()
                .setId(item.getId())
                .setName(item.getName())
                .setPrice(item.getPrice())
                .setDescription(item.getDescription())
                .setQuantity(cartItem.getQuantity())
                .setImageUrl(item.getImageUrl());
    }
}

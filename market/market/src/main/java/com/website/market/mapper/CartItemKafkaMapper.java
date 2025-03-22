package com.website.market.mapper;

import com.website.market.dto.ItemKafkaDto;
import com.website.market.dto.OrderKafkaDto;
import com.website.market.entities.Cart;
import com.website.market.entities.CartItem;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartItemKafkaMapper {

    public static OrderKafkaDto toOrderKafkaDto(Cart cart, String address, String username) {
        var itemDtos = cart.getCartItems()
                .stream()
                .map(CartItemKafkaMapper::toUserCartItemDto)
                .toList();
        return new OrderKafkaDto()
                .setItems(itemDtos)
                .setAddress(address)
                .setUsername(username);
    }

    private static ItemKafkaDto toUserCartItemDto(CartItem cartItem) {
        var item = cartItem.getItem();
        return new ItemKafkaDto()
                .setId(item.getId())
                .setName(item.getName())
                .setPrice(item.getPrice())
                .setDescription(item.getDescription())
                .setQuantity(cartItem.getQuantity())
                .setImageUrl(item.getImageUrl());


    }
}

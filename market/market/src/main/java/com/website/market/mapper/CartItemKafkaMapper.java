package com.website.market.mapper;

import com.website.market.dto.OfferItemKafkaDto;
import com.website.market.dto.OrderKafkaDto;
import com.website.market.entities.Cart;
import com.website.market.entities.CartItem;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartItemKafkaMapper {

    public static OrderKafkaDto toOrderKafkaDto(Cart cart, String address) {
        var itemDtos = cart.getCartItems()
                .stream()
                .map(CartItemKafkaMapper::toUserCartItemDto)
                .toList();
        return new OrderKafkaDto()
                .setOfferItems(itemDtos)
                .setAddress(address);


    }

    private static OfferItemKafkaDto toUserCartItemDto(CartItem cartItem) {
        var item = cartItem.getItem();
        return new OfferItemKafkaDto()
                .setId(item.getId())
                .setName(item.getName())
                .setPrice(item.getPrice())
                .setDescription(item.getDescription())
                .setQuantity(cartItem.getQuantity())
                .setImageUrl(item.getImageUrl());


    }
}

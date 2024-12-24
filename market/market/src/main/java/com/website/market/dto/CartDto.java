package com.website.market.dto;

import com.website.market.entities.CartItem;
import lombok.Data;

import java.util.List;

@Data
public class CartDto {
    private List<CartItemDto> cartItemDto;



}

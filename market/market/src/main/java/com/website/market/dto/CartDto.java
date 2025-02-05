package com.website.market.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Accessors(chain = true)
@Data
public class CartDto {
    private List<CartItemDto> items;

}

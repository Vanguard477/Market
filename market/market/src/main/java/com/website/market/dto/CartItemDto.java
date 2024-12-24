package com.website.market.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemDto {
    private String id;
    private String name;
    private String description;
    private String imageUrl;
    private BigDecimal price;
    private int quantity;

}

package com.website.market.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CatalogItemDto {
    private String id;
    private String name;
    private BigDecimal price;
    private String imageUrl;


}

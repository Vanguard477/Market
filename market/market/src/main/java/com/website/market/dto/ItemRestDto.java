package com.website.market.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class ItemRestDto {
    private String id;
    private String name;
    private String description;
    private String imageUrl;
    private BigDecimal price;
    private int quantity;
}

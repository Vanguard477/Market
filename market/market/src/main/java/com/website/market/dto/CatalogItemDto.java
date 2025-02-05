package com.website.market.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import java.math.BigDecimal;

@Accessors(chain = true)
@Data
public class CatalogItemDto {
    private String id;
    private String name;
    private BigDecimal price;
    private String imageUrl;

}

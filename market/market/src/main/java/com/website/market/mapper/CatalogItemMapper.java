package com.website.market.mapper;

import com.website.market.dto.CatalogItemDto;
import com.website.market.entities.Item;


public class CatalogItemMapper {

    public static CatalogItemDto toCatalogItemDto(Item item) {
        return new CatalogItemDto()
                .setId(item.getId())
                .setName(item.getName())
                .setPrice(item.getPrice())
                .setImageUrl(item.getImageUrl());
    }


}

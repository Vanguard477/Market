package com.website.market.service;


import com.website.market.dto.CatalogItemDto;
import com.website.market.entities.Item;
import com.website.market.repository.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    private ItemRepository itemRepository;

    public Item getItem(String id) {
        Optional<Item> item = itemRepository.findById(id);
        return item.orElseThrow(() -> new EntityNotFoundException("Item not found"));
    }

    public CatalogItemDto getAllItems(Item item) {
        CatalogItemDto catalogItemDto = new CatalogItemDto();
        catalogItemDto.setId(item.getId());
        catalogItemDto.setName(item.getName());
        catalogItemDto.setPrice(item.getPrice());
        catalogItemDto.setImageUrl(item.getImageUrl());
        return catalogItemDto;
    }

}

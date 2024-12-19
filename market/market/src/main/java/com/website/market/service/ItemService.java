package com.website.market.service;


import com.website.market.entities.Item;
import com.website.market.repository.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {
    private ItemRepository itemRepository;

    public Item getItem(String id) {
        Optional<Item> item = itemRepository.findById(id);
        return item.orElseThrow(() -> new EntityNotFoundException("Item not found"));
    }


}

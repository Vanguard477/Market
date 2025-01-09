package com.website.market.controller;


import com.website.market.dto.CatalogResponseDto;
import com.website.market.dto.FilterDto;
import com.website.market.mapper.CatalogItemMapper;
import com.website.market.repository.ItemRepository;
import com.website.market.service.CartService;
import com.website.market.service.ItemService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/catalog")
@RestController
@RequiredArgsConstructor
public class CatalogController {
    private final ItemRepository itemRepository;
    private final ItemService itemService;
    private final CartService cartService;


    @PostMapping
    public CatalogResponseDto getCatalog(@RequestBody FilterDto filterDto) {
        Pageable pageable = PageRequest.of(filterDto.getPage(), filterDto.getSize());
        var items = itemRepository.findAll(pageable)
                .stream()
                .map(CatalogItemMapper::toCatalogItemDto)
                .toList();
        return new CatalogResponseDto()
                .setItems(items);
    }

    @PutMapping("/{itemId}/add-to-cart")
    public void addItemFromCatalogToCart(String id) {
        cartService.addItemTotCurrentUserCart(itemService.getItemById(id));
    }


}

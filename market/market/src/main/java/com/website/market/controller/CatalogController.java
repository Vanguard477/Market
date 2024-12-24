package com.website.market.controller;


import com.website.market.dto.CatalogItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@RequestMapping("/catalog")
@RestController
@RequiredArgsConstructor
public class CatalogController {
    private final List<CatalogItemDto> catalogItemDto;


    @PostMapping
    public List<CatalogItemDto> catalog(
            @RequestParam(required = false, defaultValue = "1") int page
            ,@RequestParam(required = false, defaultValue = "10") int size) {

        return catalogItemDto; //(PageRequest.of(page, size));
    }

    @PutMapping("/{itemId}/add-to-cart")
    public void addItemFromCatalogToCart() {
        //добавления Item в Cart текущему пользователю
    }


}

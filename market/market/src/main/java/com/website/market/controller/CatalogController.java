package com.website.market.controller;


import com.website.market.dto.CatalogItemDto;
import com.website.market.dto.FilterDto;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@RequestMapping("/catalog")
@RestController
@RequiredArgsConstructor
public class CatalogController {
    private final CatalogItemDto catalogItemDto;


    @PostMapping
    public CatalogItemDto catalog(@RequestBody FilterDto filterDto) {

        return catalogItemDto;
    }

    @PutMapping("/{itemId}/add-to-cart")
    public void addItemFromCatalogToCart() {
        //добавления Item в Cart текущему пользователю
    }


}

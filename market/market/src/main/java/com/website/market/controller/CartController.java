package com.website.market.controller;


import com.website.market.dto.CartDto;
import com.website.market.dto.CartItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping("/cart")
@RestController
@RequiredArgsConstructor
public class CartController {
    private final List<CartDto> cartDto;


    @GetMapping
    public List<CartDto> cart() {
        return cartDto;
    }


}

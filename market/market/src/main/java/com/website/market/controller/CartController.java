package com.website.market.controller;


import com.website.market.dto.CartDto;
import com.website.market.mapper.CartMapper;
import com.website.market.repository.UserRepository;
import com.website.market.service.CurrentUserService;
import com.website.market.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/cart")
@RestController
@RequiredArgsConstructor
public class CartController {
    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping
    public CartDto userCart() {
        var userCart = userRepository.findUserWithCartById(userService.getCurrentUser().getId()).orElseThrow().getCart();
        return CartMapper.toCartDto(userCart);
    }


}

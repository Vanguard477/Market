package com.website.market.service;


import com.website.market.models.Basket;
import com.website.market.repository.BasketRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketService {
    private BasketRepository basketRepository;


    public Basket getBasketAdd(String name, String description, String imageUrl, BigDecimal price) {
        Basket basket = new Basket(name, description, price, imageUrl);
        return basketRepository.save(basket);
    }

    public Basket getBasketRemove(Long id) {
        Basket basket = basketRepository.findById(id).orElseThrow();
        basketRepository.delete(basket);
        return basket;
    }

    public Basket getBasketDetailInfo(Long id) {
        Optional<Basket> basket = basketRepository.findById(id);
        return basket.orElseThrow(() -> new EntityNotFoundException("Items not found in basket"));
    }

}

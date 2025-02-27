package com.website.market.controller;


import com.website.market.dto.AddressDto;
import com.website.market.service.OrderProcessingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderProcessingService orderProcessingService;

    @PostMapping("/send")
    public void submitOrder(@RequestBody AddressDto addressDto) {
        orderProcessingService.sendOrder(addressDto.getAddress());
    }

}

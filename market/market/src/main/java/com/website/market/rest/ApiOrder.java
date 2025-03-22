package com.website.market.rest;

import com.website.market.dto.OrderRestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "order-service", url = "http://localhost:8080")
public interface ApiOrder {

    @PostMapping("/")
    void sendOrder(@RequestParam OrderRestDto order);

}

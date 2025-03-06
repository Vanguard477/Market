package com.website.market.service;


import com.website.market.entities.User;
import com.website.market.kafkaComponent.KafkaProducer;
import com.website.market.mapper.CartItemKafkaMapper;
import com.website.market.mapper.CartItemRestMapper;
import com.website.market.repository.UserRepository;
import com.website.market.rest.ApiOrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderProcessingService {
    private final KafkaProducer kafkaProducer;
    private final UserService userService;
    private final UserRepository userRepository;
    private final ApiOrder apiOrder;

    public void sendOrderKafka(String address) {
        var user = getUser();
        var orderKafkaDto = CartItemKafkaMapper.toOrderKafkaDto(user.getCart(), address, user.getUsername());
        kafkaProducer.sendMessageToTopic(orderKafkaDto);
    }

    public void sendOrderRest(String address) {
        var user = getUser();
        apiOrder.sendOrder(CartItemRestMapper.toOrderRestDto(user.getCart(), address, user.getUsername()));
    }

    private User getUser() {
        return userRepository.findUserWithCartById(userService.getCurrentUser().getId()).orElseThrow();
    }

}


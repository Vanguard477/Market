package com.website.market.service;


import com.website.market.dto.AddressDto;
import com.website.market.kafkaComponent.KafkaProducer;
import com.website.market.mapper.CartItemKafkaMapper;
import com.website.market.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProcessingService {
    private final KafkaProducer kafkaProducer;
    private final UserService userService;
    private final UserRepository userRepository;

    public void sendOrder(String address) {
        var userCart = userRepository.findUserWithCartById(userService.getCurrentUser().getId()).orElseThrow().getCart();
        var orderKafkaDto = CartItemKafkaMapper.toOrderKafkaDto(userCart, address);
        kafkaProducer.sendMessageToTopic(orderKafkaDto);
    }


}


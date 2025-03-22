package com.website.market.kafkaComponent;

import com.website.market.service.InitService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ComponentForTheKafkaCheck {
    private final InitService initService;

    @PostConstruct
    public void creatTestUserForKafka() {
        initService.creatTestUser();
    }

}

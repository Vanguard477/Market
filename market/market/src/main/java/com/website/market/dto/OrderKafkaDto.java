package com.website.market.dto;


import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class OrderKafkaDto {
    private String address;
    private List<OfferItemKafkaDto> offerItems;
}

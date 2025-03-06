package com.website.market.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class OrderRestDto {
    private String address;
    private List<ItemRestDto> items;
    private String username;
}
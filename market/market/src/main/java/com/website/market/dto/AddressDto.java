package com.website.market.dto;

import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class AddressDto {
    private String address;
}

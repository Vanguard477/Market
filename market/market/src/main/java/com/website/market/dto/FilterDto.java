package com.website.market.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FilterDto {
    private int page;
    private int size;

    public FilterDto(int page, int size) {
        this.page = page;
        this.size = size;
    }
}

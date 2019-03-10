package com.softserve.actent.model.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CategoryDto {

    private String name;

    public CategoryDto(String name) {
        this.name = name;
    }
}


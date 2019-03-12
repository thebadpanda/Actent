package com.softserve.actent.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class CountryDto {

    @NonNull
    private String name;

    @NonNull
    private Long id;
}

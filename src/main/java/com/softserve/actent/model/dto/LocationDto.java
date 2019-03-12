package com.softserve.actent.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class LocationDto {
    @NonNull
    private String address;

    @NonNull
    private Long cityId;
}
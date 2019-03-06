package com.softserve.actent.model.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class LocationDto {
    @NonNull
    private String address;
}

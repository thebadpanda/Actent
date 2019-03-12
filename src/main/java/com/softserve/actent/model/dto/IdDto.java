package com.softserve.actent.model.dto;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class IdDto {

    @NonNull
    private Long id;
}

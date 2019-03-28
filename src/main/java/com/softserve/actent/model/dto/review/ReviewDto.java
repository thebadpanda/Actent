package com.softserve.actent.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class ReviewDto {

    @NonNull
    private Long id;

    @NonNull
    private String text;

    @NonNull
    private Integer score;
}

package com.softserve.actent.model.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class ReviewDto {
    @NonNull
    private String reviewText;

    @NonNull
    private Integer reviewScore;
}

package com.softserve.actent.model.dto;

import com.softserve.actent.constant.ExceptionMessages;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CreateReviewDto {

    @NotBlank(message = ExceptionMessages.NO_REVIEW_TEXT)
    private String text;

    @NotNull(message = ExceptionMessages.NO_REVIEW_SCORE)
    // TODO: use constants
    @Min(value = 1, message = ExceptionMessages.BAD_REVIEW_SCORE)
    @Max(value = 5, message = ExceptionMessages.BAD_REVIEW_SCORE)
    private Integer score;
}

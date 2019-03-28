package com.softserve.actent.model.dto;

import com.softserve.actent.constant.ExceptionMessages;
import com.softserve.actent.constant.NumberConstants;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CreateReviewDto {

    @NotBlank(message = ExceptionMessages.REVIEW_NO_TEXT)
    private String text;

    @NotNull(message = ExceptionMessages.REVIEW_NO_SCORE)
    @Min(value = NumberConstants.MIN_SCORE_VALUE, message = ExceptionMessages.REVIEW_BAD_SCORE)
    @Max(value = NumberConstants.MAX_SCORE_VALUE, message = ExceptionMessages.REVIEW_BAD_SCORE)
    private Integer score;
}

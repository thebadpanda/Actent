package com.softserve.actent.model.dto;

import com.softserve.actent.constant.ExceptionMessages;
import com.softserve.actent.constant.NumberConstants;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class CreateTagDto {

    @NotBlank(message = ExceptionMessages.NO_TAG_TEXT)
    @Size(min = NumberConstants.TAG_TEXT_MIN_LENGTH, message = ExceptionMessages.TOO_SHORT_TAG_TEXT)
    private String text;
}

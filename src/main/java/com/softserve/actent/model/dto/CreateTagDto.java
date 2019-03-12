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

    @NotBlank(message = ExceptionMessages.TAG_NO_TEXT)
    @Size(min = NumberConstants.TAG_TEXT_MIN_LENGTH, message = ExceptionMessages.TAG_TOO_SHORT_TEXT)
    private String text;
}

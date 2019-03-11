package com.softserve.actent.model.dto;

import com.softserve.actent.constant.ExceptionMessages;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class CreateTagDto {

    @NotBlank(message = ExceptionMessages.NO_TAG_TEXT)
    @Size(min = 3, message = ExceptionMessages.TOO_SHORT_TAG_TEXT)
    private String text;
}


package com.softserve.actent.model.dto;

import com.softserve.actent.constant.ExceptionMessages;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class AddImageDto {

    @NotBlank(message = ExceptionMessages.NO_IMAGE_FILEPATH)
    private String filePath;

    @Size(min = 64, max = 64, message = ExceptionMessages.INAPPROPRIATE_HASH_LENGTH)
    private String hash;
}

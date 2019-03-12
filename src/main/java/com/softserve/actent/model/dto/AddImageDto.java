package com.softserve.actent.model.dto;

import com.softserve.actent.constant.ExceptionMessages;
import com.softserve.actent.constant.NumberConstants;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class AddImageDto {

    @NotBlank(message = ExceptionMessages.IMAGE_NO_FILEPATH)
    private String filePath;

    @NotBlank(message = ExceptionMessages.IMAGE_NO_HASH)
    @Size(min = NumberConstants.HASH_LENGTH, max = NumberConstants.HASH_LENGTH,
            message = ExceptionMessages.IMAGE_INAPPROPRIATE_HASH_LENGTH)
    private String hash;
}

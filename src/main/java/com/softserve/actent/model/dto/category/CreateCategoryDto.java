package com.softserve.actent.model.dto.category;

import com.softserve.actent.constant.NumberConstants;
import com.softserve.actent.constant.StringConstants;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class CreateCategoryDto {

    @NotBlank(message = StringConstants.CATEGORY_NOT_BE_BLANK)
    @Size(min = NumberConstants.MIN_VALUE_FOR_CATEGORY_NAME, max = NumberConstants.MAX_VALUE_FOR_CATEGORY_NAME,
            message = StringConstants.CATEGORY_NO_LONGER_THAN_THIRTY_SYMBOLS)
    private String name;

    @Min(value = 0, message = StringConstants.CATEGORY_MESSAGE)
    private Long parentId;

}

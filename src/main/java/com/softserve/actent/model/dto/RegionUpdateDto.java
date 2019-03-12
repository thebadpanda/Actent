package com.softserve.actent.model.dto;

import com.softserve.actent.constant.NumberConstants;
import com.softserve.actent.constant.StringConstants;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class RegionUpdateDto {

    @NonNull
    @NotBlank(message = StringConstants.EMPTY_REGION)
    @Length(min = NumberConstants.REGION_MIN_LENGTH,
            max = NumberConstants.REGION_MAX_LENGTH,
            message = StringConstants.REGION_LENGTH_BETWEEN_FIRE_AND_THIRTY_SYMBOLS)
    private String name;
}

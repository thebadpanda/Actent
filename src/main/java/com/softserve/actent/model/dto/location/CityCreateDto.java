package com.softserve.actent.model.dto.location;

import com.softserve.actent.constant.NumberConstants;
import com.softserve.actent.constant.StringConstants;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
public class CityCreateDto {

    @NotBlank(message = StringConstants.EMPTY_CITY)
    @Length(min = NumberConstants.CITY_MIN_LENGTH,
            max = NumberConstants.CITY_MAX_LENGTH,
            message = StringConstants.CITY_LENGTH_BETWEEN_FOUR_AND_THIRTY_SYMBOLS)
    private String name;

    @NotNull(message = StringConstants.REGION_ID_NOT_NULL)
    @Positive(message = StringConstants.REGION_ID_POSITIVE_AND_GREATER_THAN_ZERO)
    private Long regionId;
}

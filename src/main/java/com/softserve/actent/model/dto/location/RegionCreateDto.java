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
public class RegionCreateDto {

    @NotBlank(message = StringConstants.EMPTY_REGION)
    @Length(min = NumberConstants.REGION_MIN_LENGTH,
            max = NumberConstants.REGION_MAX_LENGTH,
            message = StringConstants.REGION_LENGTH_BETWEEN_FIVE_AND_THIRTY_SYMBOLS)
    private String name;

    @NotNull(message = StringConstants.COUNTRY_ID_NOT_NULL)
    @Positive(message = StringConstants.COUNTRY_ID_POSITIVE_AND_GREATER_THAN_ZERO)
    private Long countryId;

}

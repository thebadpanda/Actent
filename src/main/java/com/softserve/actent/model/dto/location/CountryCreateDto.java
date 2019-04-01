package com.softserve.actent.model.dto.location;

import com.softserve.actent.constant.NumberConstants;
import com.softserve.actent.constant.StringConstants;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class CountryCreateDto {

    @NotBlank(message = StringConstants.EMPTY_COUNTRY)
    @Length(min = NumberConstants.COUNTRY_MIN_LENGTH,
            max = NumberConstants.COUNTRY_MAX_LENGTH,
            message = StringConstants.COUNTRY_LENGTH_BETWEEN_THREE_AND_THIRTY_SYMBOLS)
    private String name;
}


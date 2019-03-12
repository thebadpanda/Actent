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
public class LocationUpdateDto {
    @NonNull
    @NotBlank(message = StringConstants.EMPTY_LOCATION)
    @Length(min = NumberConstants.LOCATION_MIN_LENGTH,
            max = NumberConstants.LOCATION_MAX_LENGTH,
            message = StringConstants.LOCATION_LENGTH_BETWEEN_FIRE_AND_FIFTY_SYMBOLS)
    private String address;
}

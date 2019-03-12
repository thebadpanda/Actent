package com.softserve.actent.model.dto.user;

import com.softserve.actent.constant.NumberConstants;
import com.softserve.actent.constant.StringConstants;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Positive;

@Data
public class UserLocationDto {

    @Length(max = NumberConstants.USER_ADDRESS_MAX_LENGTH, message = StringConstants.USER_LOCATION_LENGHT_RANGE)
    private String address;

    @Positive(message = StringConstants.USER_CITY_ID_NEGATIVE_NUMBER)
    private Long cityId;
}

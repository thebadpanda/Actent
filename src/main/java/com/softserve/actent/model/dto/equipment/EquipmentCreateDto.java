package com.softserve.actent.model.dto.equipment;

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
public class
EquipmentCreateDto {

    @NotBlank(message = StringConstants.TITLE_SHOULD_NOT_BE_BLANK)
    @Length(min = NumberConstants.TITLE_MIN_LENGTH, max = NumberConstants.TITLE_MAX_LENGTH, message = StringConstants.TITLE_AT_LEAST_SIX_AND_NO_LONGER_THAN_HUNDRED_SYMBOLS)
    private String title;

    @Length(min = NumberConstants.DESCRIPTION_MIN_LENGTH, max = NumberConstants.DESCRIPTION_MAX_LENGTH, message = StringConstants.DESCRIPTION_AT_LEAST_SIX_AND_NO_LONGER_THAN_THOUSAND_SYMBOLS)
    private String description;

    private Boolean satisfied;

    @Positive(message = StringConstants.ASSIGNED_USER_ID_MUST_BE_POSITIVE)
    private Long assignedUserId;

    @NotNull(message = StringConstants.ASSIGNED_EVENT_ID_SHOULD_NOT_BE_NULL)
    @Positive(message = StringConstants.ASSIGNED_EVENT_ID_MUST_BE_POSITIVE)
    private Long assignedEventId;
}

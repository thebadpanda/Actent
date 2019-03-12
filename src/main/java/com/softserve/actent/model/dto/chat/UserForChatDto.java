package com.softserve.actent.model.dto.chat;

import com.softserve.actent.constant.StringConstants;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
public class UserForChatDto {

    @NotNull(message = StringConstants.EMPTY_USER_ID)
    @Positive(message = StringConstants.USER_ID_MUST_BE_POSITIVE_AND_GREATER_THAN_ZERO)
    private Long id;

    @NotBlank(message = StringConstants.EMPTY_USER_LOGIN)
    private String login;
}

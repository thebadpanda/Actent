package com.softserve.actent.model.dto.message;

import com.softserve.actent.constant.StringConstants;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class CreateTextMessageDto {

    @Positive(message = StringConstants.SENDER_ID_MUST_BE_GREATER_THAN_ZERO)
    @NotNull(message = StringConstants.SENDER_SHOULD_NOT_BE_NULL)
    private Long senderId;

    @Positive(message = StringConstants.CHAT_ID_MUST_BE_GREATER_THAN_ZERO)
    @NotNull(message = StringConstants.CHAT_ID_SHOULD_NOT_BE_NULL)
    private Long chatId;

    @NotBlank(message = StringConstants.MESSAGE_SHOULD_NOT_BE_BLANK)
    private String messageContent;

}
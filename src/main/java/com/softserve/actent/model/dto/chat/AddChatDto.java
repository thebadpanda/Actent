package com.softserve.actent.model.dto.chat;

import com.softserve.actent.constant.StringConstants;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class AddChatDto {

    @NotBlank(message = StringConstants.INVALID_CHAT_TYPE)
    private String chatType;

}

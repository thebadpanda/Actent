package com.softserve.actent.model.dto.chat;

import com.softserve.actent.constant.StringConstants;
import com.softserve.actent.model.entity.ChatType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Data
@NoArgsConstructor
public class ChatInfoDto {

    @Positive
    @NotNull(message = StringConstants.CHAT_ID_SHOULD_NOT_BE_NULL)
    private Long id;

    @NotBlank(message = StringConstants.CHAT_TYPE_NOT_BE_BLANK)
    private ChatType chatType;

    private List<UserForChatDto> bannedUserInChatDto;

}

package com.softserve.actent.model.dto.chat;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BanUserInChatDto {

    List<UserForChatDto> bannedUsers;

}

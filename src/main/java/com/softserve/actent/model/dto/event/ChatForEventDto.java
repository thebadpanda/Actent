package com.softserve.actent.model.dto.event;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.softserve.actent.model.entity.ChatType;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ChatForEventDto {

    private Long id;
    private ChatType type;
    private List<UserForEventDto> bannedUsers;
}

package com.softserve.actent.model.dto.eventUser;

import com.softserve.actent.model.dto.event.UserForEventDto;
import com.softserve.actent.model.entity.EventUserType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EventUserForEventDto {

    private Long id;
    private UserForEventDto userForEventDto;
    private EventUserType eventUserType;
}

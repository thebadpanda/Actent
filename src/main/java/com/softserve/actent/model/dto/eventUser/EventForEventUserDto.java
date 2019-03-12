package com.softserve.actent.model.dto.eventUser;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EventForEventUserDto {

    private Long id;
    private String title;
    private String description;
    private String creationDate;
}

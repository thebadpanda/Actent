package com.softserve.actent.model.dto.equipment;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EquipmentDto {

    private Long id;
    private String title;
    private String description;
    private Boolean satisfied;
    private String assignedUserFirstName;
    private String assignedUserLastName;
    private String assignedEventTitle;
    private Long assignedEventId;
    private Long assignedUserId;
}

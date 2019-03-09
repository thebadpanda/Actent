package com.softserve.actent.model.dto.equipment;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class EquipmentDto {

    @NonNull
    private Long id;

    @NonNull
    private String title;

    @NonNull
    private String description;

    private Boolean satisfied;

    private String assignedUserFirstName;

    private String assignedUserLastName;

    private String assignedEventTitle;

}

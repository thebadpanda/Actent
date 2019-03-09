package com.softserve.actent.model.dto.equipment;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class CreateEquipmentDto {

    @NonNull
    private String title;

    @NonNull
    private String description;

    @NonNull
    private Boolean satisfied;

    private Long assignedUserId;

    @NonNull
    private Long assignedEventId;

}

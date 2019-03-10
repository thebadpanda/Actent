package com.softserve.actent.model.dto.equipment;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Null;

@Data
@NoArgsConstructor
public class EquipmentCreateDto {

    @Null
    private Long id;

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

package com.softserve.actent.model.dto.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegionForEventDto {

    private Long id;
    private String name;

    @JsonProperty("City")
    private CityForEventDto cityForEventDto;
}

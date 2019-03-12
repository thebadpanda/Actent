package com.softserve.actent.model.dto.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CountryForEventDto {

    private Long id;
    private String name;

    @JsonProperty("Region")
    private RegionForEventDto regionForEventDto;
}

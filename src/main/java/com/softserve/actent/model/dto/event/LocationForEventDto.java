package com.softserve.actent.model.dto.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LocationForEventDto {

    private Long id;
    private String address;

    @JsonProperty("Country")
    private CountryForEventDto countryForEventDto;
}

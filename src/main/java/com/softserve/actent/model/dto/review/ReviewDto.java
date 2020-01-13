package com.softserve.actent.model.dto.review;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {

    private Long id;

    private String text;

    private Integer score;

    @JsonProperty("first_name")
    private String authorFirstName;

    @JsonProperty("last_name")
    private String authorLastName;

    @JsonProperty("city")
    private String authorLocationCityName;

    @JsonProperty("region")
    private String authorLocationCityRegionName;

    @JsonProperty("country")
    private String authorLocationCityRegionCountryName;

    @JsonProperty("avatar")
    private String authorAvatarFilePath;
}

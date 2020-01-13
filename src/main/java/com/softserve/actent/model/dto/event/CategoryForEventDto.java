package com.softserve.actent.model.dto.event;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryForEventDto {

    private Long id;
    private String name;

    @JsonProperty("Parent id")
    private Long parentId;
}

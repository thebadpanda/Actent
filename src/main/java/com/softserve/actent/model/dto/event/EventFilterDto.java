package com.softserve.actent.model.dto.event;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class EventFilterDto {

    private String title;

    private String cityName;

    private List<Long> categoryId;

    private Long dateFrom;

    private Long dateTo;
}
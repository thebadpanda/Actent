package com.softserve.actent.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class ImageDto {

    @NonNull
    private String filePath;

    @NonNull
    private String hash;
}

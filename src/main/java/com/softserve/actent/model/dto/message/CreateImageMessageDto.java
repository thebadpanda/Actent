package com.softserve.actent.model.dto.message;

import lombok.Data;
import lombok.NonNull;

@Data
public class CreateImageMessageDto {

    @NonNull
    private String imageFilePath;

    @NonNull
    private String imageHash;

    @NonNull
    private Long senderId;

    @NonNull
    private Long chatId;
}

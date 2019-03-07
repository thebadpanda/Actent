package com.softserve.actent.model.dto.message;

import lombok.Data;

@Data
public class CreateTextMessageDto {

    private Long senderId;

    private Long chatId;

    private String messageContent;

}
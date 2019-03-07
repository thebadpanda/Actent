package com.softserve.actent.model.dto.message;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class ViewTextMessageDto extends ViewMessageDto {

    private String messageContent;

}

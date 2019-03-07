package com.softserve.actent.model.dto.message;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class ViewImageMessageDto extends ViewMessageDto {

    private String imageFilePath;


}

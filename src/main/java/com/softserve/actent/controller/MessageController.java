package com.softserve.actent.controller;

import com.softserve.actent.constant.StringConstants;
import com.softserve.actent.model.dto.converter.ViewMessageConverter;
import com.softserve.actent.model.dto.message.CreateImageMessageDto;
import com.softserve.actent.model.dto.message.CreateTextMessageDto;
import com.softserve.actent.model.dto.message.ViewMessageDto;
import com.softserve.actent.model.entity.Message;
import com.softserve.actent.service.ChatService;
import com.softserve.actent.service.MessageService;
import com.softserve.actent.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1")
public class MessageController {

    private final MessageService messageService;

    private final UserService userService;

    private final ChatService chatService;

    private final ViewMessageConverter viewMessageConverter;


    private final ModelMapper modelMapper;

    @Autowired
    public MessageController(MessageService messageService,
                             ViewMessageConverter viewMessageConverter,
                             ModelMapper modelMapper,
                             UserService userService,
                             ChatService chatService) {

        this.messageService = messageService;
        this.viewMessageConverter = viewMessageConverter;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.chatService = chatService;
    }


    @PostMapping(value = "/textMessages")
    @ResponseStatus(HttpStatus.CREATED)
    public ViewMessageDto addMessage(@Validated @RequestBody CreateTextMessageDto createMessageDto) {

        Message message = modelMapper.map(createMessageDto, Message.class);
        message.setSender(userService.get(createMessageDto.getSenderId()));
        message.setChat(chatService.getChatById(createMessageDto.getChatId()));

        return viewMessageConverter.convertToDto(messageService.add(message));
    }

    @PostMapping(value = "/imageMessages")
    @ResponseStatus(HttpStatus.CREATED)
    public ViewMessageDto addImage(@Validated @RequestBody CreateImageMessageDto createImageMessageDto) {
        Message message = modelMapper.map(createImageMessageDto, Message.class);
        message.setSender(userService.get(createImageMessageDto.getSenderId()));
        message.setChat(chatService.getChatById(createImageMessageDto.getChatId()));

        return viewMessageConverter.convertToDto(messageService.addImageMessage(message));
    }

    @GetMapping(value = "/messages/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<ViewMessageDto> getMessagesByChatId(@PathVariable @NotNull(message = StringConstants.CHAT_ID_SHOULD_NOT_BE_NULL)
                                                    @Positive(message = StringConstants.CHAT_ID_SHOULD_BE_POSITIVE) Long id) {

        return viewMessageConverter.convertToDto(messageService.getAllMessagesByChatId(id));
    }

    @PutMapping(value = "/messages/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ViewMessageDto updateMessage(@PathVariable @NotNull
                                        @Positive(message = StringConstants.MESSAGE_ID_SHOULD_BE_POSITIVE) Long id,
                                        @Validated @RequestBody CreateTextMessageDto createMessageDto) {

        Message message = messageService.update(modelMapper.map(createMessageDto, Message.class), id);
        message.setSender(userService.get(createMessageDto.getSenderId()));
        message.setChat(chatService.getChatById(createMessageDto.getChatId()));
        return viewMessageConverter.convertToDto(message);
    }

    @DeleteMapping(value = "/textMessages/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMessageById(@PathVariable @NotNull
                                  @Positive(message = StringConstants.MESSAGE_ID_SHOULD_BE_POSITIVE) Long id) {

        messageService.delete(id);

    }

}

package com.softserve.actent.controller;

import com.softserve.actent.model.dto.converter.ImageMessageConverter;
import com.softserve.actent.model.dto.converter.TextMessageConvert;
import com.softserve.actent.model.dto.converter.ViewMessageConverter;
import com.softserve.actent.model.dto.message.CreateImageMessageDto;
import com.softserve.actent.model.dto.message.CreateTextMessageDto;
import com.softserve.actent.model.dto.message.ViewMessageDto;
import com.softserve.actent.model.entity.Message;
import com.softserve.actent.service.MessageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MessageController {

    private final MessageService messageService;

    private final ViewMessageConverter viewMessageConverter;

    private final TextMessageConvert textMessageConvert;

    private final ModelMapper modelMapper;

    private final ImageMessageConverter imageMessageConverter;

    @Autowired
    public MessageController(MessageService messageService,
                             ViewMessageConverter viewMessageConverter,
                             TextMessageConvert textMessageConvert, ModelMapper modelMapper, ImageMessageConverter imageMessageConverter) {
        this.messageService = messageService;
        this.viewMessageConverter = viewMessageConverter;
        this.textMessageConvert = textMessageConvert;
        this.modelMapper = modelMapper;
        this.imageMessageConverter = imageMessageConverter;
    }


    @PostMapping(value = "/textMessages")
    public ResponseEntity<ViewMessageDto> addMessage(@RequestBody CreateTextMessageDto createMessageDto) {

        Message message = messageService.add(textMessageConvert.convertToEntity(createMessageDto));

        return new ResponseEntity<>(viewMessageConverter.convertToDto(message), HttpStatus.CREATED);
    }

    @PostMapping(value = "/imageMessages")
    public ResponseEntity<ViewMessageDto> addImage(@RequestBody CreateImageMessageDto createImageMessageDto) {

        Message message = messageService.addImageMessage(imageMessageConverter.convertToEntity(createImageMessageDto));

        return new ResponseEntity<>(viewMessageConverter.convertToDto(message), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/textMessages/{id}")
    public ResponseEntity<Void> deleteMessageById(@PathVariable Long id) {

        messageService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/messages")
    public ResponseEntity<List<ViewMessageDto>> getMessages() {

        return new ResponseEntity<>((viewMessageConverter.convertToDto(messageService.getAll())), HttpStatus.OK);
    }

    @GetMapping(value = "/messages/{id}")
    public ResponseEntity<List<ViewMessageDto>> getMessagesByChatId(@PathVariable Long id) {

        return new ResponseEntity<>((viewMessageConverter.convertToDto(messageService.getAllMessagesByChatId(id))), HttpStatus.OK);
    }

    @PutMapping(value = "/messages/{id}")
    public ResponseEntity<CreateTextMessageDto> updateMessage(@PathVariable Long id,
                                                              @RequestBody CreateTextMessageDto createMessageDto) {
        Message message = messageService.update(modelMapper.map(createMessageDto, Message.class), id);

        return new ResponseEntity<>((textMessageConvert.convertToDto(message)), HttpStatus.OK);
    }


}

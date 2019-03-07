package com.softserve.actent.controller;

import com.softserve.actent.model.dto.IdDto;
import com.softserve.actent.model.dto.ImageDto;
import com.softserve.actent.model.dto.converter.TextMessageConvert;
import com.softserve.actent.model.dto.converter.ViewMessageConverter;
import com.softserve.actent.model.dto.message.CreateTextMessageDto;
import com.softserve.actent.model.dto.message.ViewMessageDto;
import com.softserve.actent.model.entity.Image;
import com.softserve.actent.model.entity.Message;
import com.softserve.actent.service.ImageService;
import com.softserve.actent.service.MessageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MessageController {

    private final MessageService messageService;

    private final ViewMessageConverter viewMessageConverter;

    private final TextMessageConvert textMessageConvert;

    private final ModelMapper modelMapper;

    @Autowired
    ImageService imageService;

    @Autowired
    public MessageController(MessageService messageService,
                             ViewMessageConverter viewMessageConverter,
                             TextMessageConvert textMessageConvert, ModelMapper modelMapper) {
        this.messageService = messageService;
        this.viewMessageConverter = viewMessageConverter;
        this.textMessageConvert = textMessageConvert;
        this.modelMapper = modelMapper;
    }


    @PostMapping(value = "/textMessages")
    public ResponseEntity<ViewMessageDto> addMessage(@RequestBody CreateTextMessageDto createMessageDto) {
        Message message = messageService.addTextMessage(textMessageConvert.convertToEntity(createMessageDto));
        return new ResponseEntity<>(viewMessageConverter.convertToDto(message), HttpStatus.CREATED);
    }

    @PostMapping(value = "/imageMessage")
    public ResponseEntity<ViewMessageDto> addImage(@RequestBody ImageDto addImageDto) {

        Image image = modelMapper.map(addImageDto, Image.class);
        Message message = messageService.addImageMessage(image);

        return new ResponseEntity<>(viewMessageConverter.convertToDto(message), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/textMessages/{id}")
    public ResponseEntity<Void> deleteMessageById(@PathVariable Long id) {
        messageService.deleteMessageById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/messages")
    public ResponseEntity<List<ViewMessageDto>> getMessages() {

        return new ResponseEntity<>((viewMessageConverter.convertToDto(messageService.getMessages())), HttpStatus.OK);
    }

    @GetMapping(value = "/messages/{id}")
    public ResponseEntity<List<ViewMessageDto>> getMessagesByChatId(@PathVariable Long id) {

        return new ResponseEntity<>((viewMessageConverter.convertToDto(messageService.getAllMessagesByChatId(id))), HttpStatus.OK);
    }

    @PutMapping(value = "/messages/{id}")
    public ResponseEntity<CreateTextMessageDto> updateMessage(@PathVariable Long id,
                                                              @RequestBody CreateTextMessageDto createMessageDto) {

        Message message = messageService.updateMessage(id, createMessageDto);
        messageService.addTextMessage(message);
        return new ResponseEntity<>((textMessageConvert.convertToDto(message)), HttpStatus.OK);
    }


}

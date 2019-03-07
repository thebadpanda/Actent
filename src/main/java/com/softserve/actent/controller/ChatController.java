package com.softserve.actent.controller;

import com.softserve.actent.model.dto.AddChatDto;
import com.softserve.actent.model.dto.IdDto;
import com.softserve.actent.model.entity.Chat;
import com.softserve.actent.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping(value = "/chats")
    public IdDto addEventChat(@RequestBody AddChatDto addChatDto) {
        Chat chat = chatService.addChat(addChatDto.getChatType());
        return new IdDto(chat.getId());
    }

    @GetMapping(value = "chats/{chatId}")
    public Chat getEventChat(@PathVariable(value = "chatId") Long chatId){
        return chatService.getChatById(chatId);
    }

}

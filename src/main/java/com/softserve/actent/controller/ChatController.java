package com.softserve.actent.controller;

import com.softserve.actent.model.dto.AddChatDto;
import com.softserve.actent.model.dto.IdDto;
import com.softserve.actent.model.entity.Chat;
import com.softserve.actent.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
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

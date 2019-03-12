package com.softserve.actent.controller;

import com.softserve.actent.model.dto.IdDto;
import com.softserve.actent.model.dto.chat.AddChatDto;
import com.softserve.actent.model.dto.chat.UserForChatDto;
import com.softserve.actent.model.entity.Chat;
import com.softserve.actent.model.entity.User;
import com.softserve.actent.service.ChatService;
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
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping("/api/v1")
public class ChatController {

    private final ChatService chatService;
    private final ModelMapper modelMapper;

    @Autowired
    public ChatController(ChatService chatService, ModelMapper modelMapper) {
        this.chatService = chatService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(value = "/chats")
    @ResponseStatus(HttpStatus.CREATED)
    public IdDto addEventChat(@Validated @RequestBody AddChatDto addChatDto) {
        Chat chat = chatService.addChat(addChatDto.getChatType());
        return new IdDto(chat.getId());
    }

    @GetMapping(value = "/chats/{chatId}")
    @ResponseStatus(HttpStatus.OK)
    public Chat getEventChat(@PathVariable(value = "chatId") @NotNull @Positive Long chatId){
        return chatService.getChatById(chatId);
    }

    @DeleteMapping(value = "/chats/{chatId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEventChatById(@PathVariable(value = "chatId") @NotNull @Positive Long chatId){
        chatService.deleteChatById(chatId);
    }

    @PutMapping(value = "chats/{chatId}/bannedUsers/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<UserForChatDto> banUserByChatAndUserId(@PathVariable(value = "chatId") @NotNull @Positive Long chatId,
                                                       @PathVariable(value = "userId") @NotNull @Positive Long userId){

        Chat chat = chatService.banUserInChat(chatId, userId);
        List<User> bannedUsers = chat.getBannedUsers();
        return bannedUsers.stream()
                .map(user -> modelMapper.map(user, UserForChatDto.class))
                .collect(Collectors.toList());
    }

}

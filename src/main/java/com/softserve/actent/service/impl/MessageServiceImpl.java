package com.softserve.actent.service.impl;

import com.softserve.actent.model.dto.message.CreateTextMessageDto;
import com.softserve.actent.model.entity.Image;
import com.softserve.actent.model.entity.Message;
import com.softserve.actent.model.entity.MessageType;
import com.softserve.actent.repository.MessageRepository;
import com.softserve.actent.service.ImageService;
import com.softserve.actent.service.MessageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {

    private final
    MessageRepository messageRepository;

    @Autowired
    ImageService imageService;

    private final
    ModelMapper modelMapper;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, ModelMapper modelMapper) {
        this.messageRepository = messageRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public Message addTextMessage(Message message) {
        message.setMessageType(MessageType.TEXT);
        return messageRepository.save(message);
    }

    @Override
    @Transactional
    public Message addImageMessage(Image image) {
        Message message = new Message();
        message.setMessageType(MessageType.IMAGE);
        message.setImage(imageService.addImage(image));
        return messageRepository.save(message);
    }

    @Override
    public List<Message> getMessages() {
        return messageRepository.findAll();
    }


    @Override
    @Transactional
    public void deleteMessageById(Long id) {
        Optional<Message> optionalMessage = messageRepository.findById(id);
        if (optionalMessage.isPresent()) {
            messageRepository.deleteById(id);
        }
        // todo throw exception
    }

    @Override
    public Message findById(Long id) {
        return messageRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Message updateMessage(Long id, CreateTextMessageDto createMessageDto) {
        Optional<Message> optionalMessage = messageRepository.findById(id);
        if (optionalMessage.isPresent()) {
            modelMapper.map(createMessageDto, optionalMessage.get());
            messageRepository.save(optionalMessage.get());
        }
        return optionalMessage.orElse(null);
    }

    @Override
    public List<Message> getAllMessagesByChatId(Long id) {
        return messageRepository.findAllByChatId(id);
    }

}

package com.softserve.actent.service.impl;

import com.softserve.actent.exceptions.ResourceNotFoundException;
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

import static com.softserve.actent.exceptions.codes.ExceptionCode.MESSAGE_NOT_FOUND;

@Service
public class MessageServiceImpl implements MessageService {

    private final
    MessageRepository messageRepository;

    @Autowired
    ImageService imageService;


    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, ModelMapper modelMapper) {
        this.messageRepository = messageRepository;
    }

    @Override
    @Transactional
    public Message add(Message message) {
        message.setMessageType(MessageType.TEXT);
        return messageRepository.save(message);
    }

    @Override
    @Transactional
    public Message addImageMessage(Message message) {
        Image image = new Image();
        image.setHash(message.getImage().getHash());
        image.setFilePath(message.getImage().getFilePath());
        message.setMessageType(MessageType.IMAGE);
        message.setImage(imageService.add(image));
        return messageRepository.save(message);
    }

    @Override
    public List<Message> getAll() {
        return messageRepository.findAll();
    }


    @Override
    @Transactional
    public void delete(Long id) {
        Optional<Message> optionalMessage = messageRepository.findById(id);
        if (optionalMessage.isPresent()) {
            messageRepository.deleteById(id);
        }
        // todo throw exception
    }

    @Override
    public Message get(Long id) {
        return messageRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Message not found", MESSAGE_NOT_FOUND));
    }

    @Override
    @Transactional
    public Message update(Message message, Long id) {

        if (messageRepository.existsById(id)) {
            message.setId(id);
            return messageRepository.save(message);
        } else {
            throw new ResourceNotFoundException("Message not found", MESSAGE_NOT_FOUND);
        }
    }

    @Override
    public List<Message> getAllMessagesByChatId(Long id) {
        return messageRepository.findAllByChatId(id);
    }

}

package com.softserve.actent.service.impl;

import com.softserve.actent.constant.ExceptionMessages;
import com.softserve.actent.constant.NumberConstants;
import com.softserve.actent.exceptions.ResourceNotFoundException;
import com.softserve.actent.exceptions.codes.ExceptionCode;
import com.softserve.actent.exceptions.validation.MessageValidationException;
import com.softserve.actent.model.entity.Image;
import com.softserve.actent.model.entity.Message;
import com.softserve.actent.model.entity.MessageType;
import com.softserve.actent.repository.MessageRepository;
import com.softserve.actent.service.ImageService;
import com.softserve.actent.service.MessageService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.softserve.actent.exceptions.codes.ExceptionCode.MESSAGE_NOT_FOUND;

@Service
@NoArgsConstructor
public class MessageServiceImpl implements MessageService {

    private MessageRepository messageRepository;
    private ImageService imageService;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, ImageService imageService) {
        this.messageRepository = messageRepository;
        this.imageService = imageService;
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

        List<Message> messages = messageRepository.findAll();
        if (messages.isEmpty()) {

            throw new ResourceNotFoundException(
                    ExceptionMessages.MESSAGE_NOT_FOUND,
                    MESSAGE_NOT_FOUND
            );
        } else {

            return messages;
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Optional<Message> optionalMessage = messageRepository.findById(id);
        if (optionalMessage.isPresent()) {
            messageRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException(ExceptionMessages.MESSAGE_NOT_FOUND, MESSAGE_NOT_FOUND);
        }
    }

    @Override
    public Message get(Long id) {
        return messageRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(ExceptionMessages.MESSAGE_NOT_FOUND, MESSAGE_NOT_FOUND));
    }

    @Override
    @Transactional
    public Message update(Message message, Long id) {
        Optional<Message> optionalMessage = messageRepository.findById(id);

        if (optionalMessage.isPresent() && checkCredential(optionalMessage, message)) {

            message.setId(id);
            message.setMessageType(MessageType.TEXT);
            return messageRepository.save(message);
        } else {
            throw new ResourceNotFoundException(ExceptionMessages.MESSAGE_NOT_FOUND, MESSAGE_NOT_FOUND);
        }
    }

    @Override
    public List<Message> getNextThirtyMessagesByChatId(Long id, int pageNumber) {

        List<Message> messages = messageRepository.findAllByChatIdOrderBySendTimeDesc(id, PageRequest.of(pageNumber, NumberConstants.SHOW_THIRTY_MESSAGES_PER_PAGE));

        return messages;

    }

    private boolean checkCredential(Optional<Message> optionalMessage, Message message) {

        if (optionalMessage.get().getChat().getId().equals(message.getChat().getId()) &&
                optionalMessage.get().getSender().getId().equals(message.getSender().getId())) {
            return true;
        } else
            throw new MessageValidationException(ExceptionMessages.YOU_CAN_NOT_CHANGE_THIS_MESSAGE,
                    ExceptionCode.VALIDATION_FAILED);


    }

}

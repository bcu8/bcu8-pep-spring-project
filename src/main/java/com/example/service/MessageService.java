package com.example.service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;
import com.example.repository.AccountRepository;
import com.example.exception.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private AccountRepository accountRepository;

    public Message insertMessage(Message message)
    {
        if (message.getMessageText().isEmpty()) throw new BadRequestException("Message text cannot be empty");
        if (message.getMessageText().length() > 255) throw new BadRequestException("Message text cannot be longer than 255 characters");
        if (accountRepository.findById(message.getPostedBy()).isEmpty()) throw new BadRequestException("Account posting message does not exist");
        return messageRepository.save(message);
    }

    public List<Message> getAllMessages()
    {
        return messageRepository.findAll();
    }

    public Message getMessageById(int id)
    {
        return messageRepository.findById(id);
    }

    public int deleteMessage(int id)
    {
        Message message = getMessageById(id);
        if (message == null)
        {
            return 0;
        }
        messageRepository.deleteById(id);
        return 1;
    }

    public int updateMessage(int id, String messageText)
    {
        if (messageText.isEmpty()) throw new BadRequestException("Message text cannot be empty");
        if (messageText.length() > 255) throw new BadRequestException("Message text cannot be longer than 255 characters");
        if (messageRepository.findById(id) == null) throw new BadRequestException("No message with that ID was found");
        int rowsAffected = messageRepository.updateMessageTextById(id, messageText);
        return rowsAffected;
    }

    public List<Message> getMessagesFromUser(int accountId)
    {
        return messageRepository.findByPostedBy(accountId);
    }
}

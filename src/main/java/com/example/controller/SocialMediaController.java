package com.example.controller;

import com.example.entity.*;
import com.example.service.*;
//All exceptions are handled with the global exception handler, thus exception handling
//is ommitted from this class
import com.example.exception.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private MessageService messageService;

    @PostMapping("/register")
    public ResponseEntity<Account> registerAccount(@RequestBody Account account)
    {
        Account newAccount = accountService.registerAccount(account);
        return new ResponseEntity<>(newAccount, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Account> login(@RequestBody Account account)
    {
        Account validAccount = accountService.login(account);
        return new ResponseEntity<>(validAccount, HttpStatus.OK);
    }

    @PostMapping("/messages")
    public ResponseEntity<Message> processNewMessage(@RequestBody Message message)
    {
        Message newMessage = messageService.insertMessage(message);
        return new ResponseEntity<>(newMessage, HttpStatus.OK);
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages()
    {
        List<Message> messages = messageService.getAllMessages();
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @GetMapping("/messages/{messageId}")
    public ResponseEntity<Message> getMessageById(@PathVariable int messageId)
    {
        Message message = messageService.getMessageById(messageId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<Integer> deleteMessage(@PathVariable int messageId)
    {
        int rowsAffected = messageService.deleteMessage(messageId);
        if (rowsAffected > 0)return new ResponseEntity<>(rowsAffected, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/messages/{messageId}")
    public ResponseEntity<Integer> updateMessage(@PathVariable int messageId, @RequestBody Message message)
    {
        int rowsAffected = messageService.updateMessage(messageId, message.getMessageText());
        if (rowsAffected > 0) return new ResponseEntity<>(rowsAffected, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("accounts/{accountId}/messages")
    public ResponseEntity<List<Message>> getMessagesFromUser(@PathVariable Integer accountId)
    {
        List<Message> messages = messageService.getMessagesFromUser(accountId);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }
}

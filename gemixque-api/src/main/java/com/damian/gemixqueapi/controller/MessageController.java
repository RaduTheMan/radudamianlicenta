package com.damian.gemixqueapi.controller;

import com.damian.gemixqueapi.entity.MessageEntity;
import com.damian.gemixqueapi.exception.ResourceNotFoundException;
import com.damian.gemixqueapi.projection.user.GetMessagesReceivedByUserInterfaceProjection;
import com.damian.gemixqueapi.projection.user.GetMessagesSentFromUserInterfaceProjection;
import com.damian.gemixqueapi.repository.MessageRepository;
import com.damian.gemixqueapi.repository.MessagesReceivedByUserRepository;
import com.damian.gemixqueapi.repository.MessagesSentFromUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MessagesReceivedByUserRepository messagesReceivedByUserRepository;

    @Autowired
    private MessagesSentFromUserRepository messagesSentFromUserRepository;

//    @RequestMapping("/messages")
//    public List<MessageEntity> getAllMessages(){
//        return messageRepository.findAll();
//    }
//
//    @RequestMapping("/messages/{id}")
//    public MessageEntity getMessage(@PathVariable Long id){
//        return messageRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
//    }

    @RequestMapping("/users/{userId}/sent-messages")
    public GetMessagesSentFromUserInterfaceProjection getMessagesSentFromUser(@PathVariable String userId){
        return messagesSentFromUserRepository.findByUuid(userId).orElseThrow(ResourceNotFoundException::new);
    }

    @RequestMapping("/users/{userId}/received-messages")
    public GetMessagesReceivedByUserInterfaceProjection getMessagesReceivedByUser(@PathVariable String userId){
        return messagesReceivedByUserRepository.findByUuid((userId)).orElseThrow(ResourceNotFoundException::new);
    }
}

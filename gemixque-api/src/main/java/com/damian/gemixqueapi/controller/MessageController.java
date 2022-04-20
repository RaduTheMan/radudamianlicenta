package com.damian.gemixqueapi.controller;

import com.damian.gemixqueapi.exception.ResourceNotFoundException;
import com.damian.gemixqueapi.projection.user.GetMessagesReceivedByUserInterfaceProjection;
import com.damian.gemixqueapi.projection.user.GetMessagesSentFromUserInterfaceProjection;
import com.damian.gemixqueapi.repository.MessagesReceivedByUserRepository;
import com.damian.gemixqueapi.repository.MessagesSentFromUserRepository;
import com.damian.gemixqueapi.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @RequestMapping("/users/{userId}/sent-messages")
    public GetMessagesSentFromUserInterfaceProjection getMessagesSentFromUser(@PathVariable String userId){
        return messageService.getMessagesSentFromUser(userId).orElseThrow(ResourceNotFoundException::new);
    }

    @RequestMapping("/users/{userId}/received-messages")
    public GetMessagesReceivedByUserInterfaceProjection getMessagesReceivedByUser(@PathVariable String userId){
        return messageService.getMessagesReceivedByUser(userId).orElseThrow(ResourceNotFoundException::new);
    }
}

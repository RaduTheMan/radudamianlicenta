package com.damian.gemixqueapi.controller;

import com.damian.gemixqueapi.entity.MessageEntity;
import com.damian.gemixqueapi.exception.ResourceNotFoundException;
import com.damian.gemixqueapi.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @RequestMapping("/messages")
    public List<MessageEntity> getAllMessages(){
        return messageRepository.findAll();
    }

    @RequestMapping("/messages/{id}")
    public MessageEntity getMessage(@PathVariable Long id){
        return messageRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }
}

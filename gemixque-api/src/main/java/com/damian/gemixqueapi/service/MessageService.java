package com.damian.gemixqueapi.service;

import com.damian.gemixqueapi.projection.user.GetMessagesReceivedByUserInterfaceProjection;
import com.damian.gemixqueapi.projection.user.GetMessagesSentFromUserInterfaceProjection;
import com.damian.gemixqueapi.repository.MessagesReceivedByUserRepository;
import com.damian.gemixqueapi.repository.MessagesSentFromUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessagesReceivedByUserRepository messagesReceivedByUserRepository;

    @Autowired
    private MessagesSentFromUserRepository messagesSentFromUserRepository;

    public Optional<GetMessagesSentFromUserInterfaceProjection> getMessagesSentFromUser(String userId){
        return messagesSentFromUserRepository.findByUuid(userId);
    }

    public Optional<GetMessagesReceivedByUserInterfaceProjection> getMessagesReceivedByUser(String userId){
        return messagesReceivedByUserRepository.findByUuid(userId);
    }
}

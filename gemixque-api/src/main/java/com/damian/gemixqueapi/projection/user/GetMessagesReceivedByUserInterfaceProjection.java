package com.damian.gemixqueapi.projection.user;

import com.damian.gemixqueapi.projection.message.MessageReceivedByUserInterfaceProjection;

import java.util.List;

public interface GetMessagesReceivedByUserInterfaceProjection {
    List<MessageReceivedByUserInterfaceProjection> getReceivedMessages();
}

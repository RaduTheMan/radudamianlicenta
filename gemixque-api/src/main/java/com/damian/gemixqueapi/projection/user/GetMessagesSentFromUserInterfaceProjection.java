package com.damian.gemixqueapi.projection.user;

import com.damian.gemixqueapi.projection.message.MessageSentFromUserInterfaceProjection;

import java.util.List;

public interface GetMessagesSentFromUserInterfaceProjection {
    List<MessageSentFromUserInterfaceProjection> getSentMessages();
}

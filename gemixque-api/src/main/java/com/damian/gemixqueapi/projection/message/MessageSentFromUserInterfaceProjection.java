package com.damian.gemixqueapi.projection.message;

import com.damian.gemixqueapi.projection.user.UserIdInterfaceProjection;

public interface MessageSentFromUserInterfaceProjection extends MessageInterfaceProjection {
    UserIdInterfaceProjection getReceiver();
}

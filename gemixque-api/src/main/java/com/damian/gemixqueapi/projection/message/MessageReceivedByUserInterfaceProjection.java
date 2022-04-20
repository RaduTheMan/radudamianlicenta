package com.damian.gemixqueapi.projection.message;

import com.damian.gemixqueapi.projection.user.UserIdInterfaceProjection;

public interface MessageReceivedByUserInterfaceProjection extends MessageInterfaceProjection {
    UserIdInterfaceProjection getSender();
}

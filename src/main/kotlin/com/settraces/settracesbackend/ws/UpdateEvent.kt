package com.settraces.settracesbackend.ws

import org.springframework.web.socket.TextMessage

abstract class UpdateEvent : IUpdateEvent {
    override fun getTextMessage(): TextMessage {
        return TextMessage(stringify())
    }
}
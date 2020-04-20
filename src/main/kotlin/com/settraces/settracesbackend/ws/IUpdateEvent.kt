package com.settraces.settracesbackend.ws

import org.springframework.web.socket.TextMessage

interface IUpdateEvent {
    fun stringify(): String
    fun getTextMessage(): TextMessage
}
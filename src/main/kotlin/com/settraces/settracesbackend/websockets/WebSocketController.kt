package com.settraces.settracesbackend.websockets

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller
import java.text.SimpleDateFormat
import java.util.*


@Controller
class WebSocketController {
    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    @Throws(Exception::class)
    fun send(message: Message): OutputMessage? {
        val time = SimpleDateFormat("HH:mm").format(Date())
        return OutputMessage(message.from!!, message.text!!, time)
    }
}
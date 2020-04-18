package com.settraces.settracesbackend.ws

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller
import java.text.SimpleDateFormat
import java.util.*


@Controller
class WebSocketController {
    @MessageMapping("/test")
    @SendTo("/topic/messages")
    @Throws(Exception::class)
    fun send(message: Message): OutputMessage? {
        println("got message")
        val time = SimpleDateFormat("HH:mm").format(Date())
        return OutputMessage(message.from!!, message.text!!, time)
    }
}
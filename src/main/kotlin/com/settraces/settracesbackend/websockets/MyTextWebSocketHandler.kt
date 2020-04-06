package com.settraces.settracesbackend.websockets

import org.slf4j.LoggerFactory
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.io.IOException
import java.util.concurrent.CopyOnWriteArrayList
import java.util.function.Consumer

class MyTextWebSocketHandler : TextWebSocketHandler() {
    private val sessions: MutableList<WebSocketSession> = CopyOnWriteArrayList()

    @Throws(Exception::class)
    override fun afterConnectionEstablished(session: WebSocketSession) {
        println("trying to coonnect")
        sessions.add(session)
        super.afterConnectionEstablished(session)
    }

    @Throws(Exception::class)
    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        println("acc" +
                "" +
                "" +
                "")
        sessions.remove(session)
        super.afterConnectionClosed(session, status)
    }

    @Throws(Exception::class)
    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        println("handling line")
        super.handleTextMessage(session, message)
        sessions.forEach(Consumer { webSocketSession: WebSocketSession ->
            try {
                webSocketSession.sendMessage(message)
            } catch (e: IOException) {
                LOGGER.error("Error occurred.", e)
            }
        })
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(MyTextWebSocketHandler::class.java)
    }
}
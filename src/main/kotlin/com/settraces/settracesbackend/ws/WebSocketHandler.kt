package com.settraces.settracesbackend.ws

import org.springframework.stereotype.Component
import org.springframework.web.socket.BinaryMessage
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.AbstractWebSocketHandler
import java.io.IOException

@Component
class WebSocketHandler : AbstractWebSocketHandler() {

    companion object {
        val sessions: ArrayList<WsClient> = ArrayList()
    }

    fun sendToProject(sessionId: String, updateEvent: UpdateEvent): Unit {
        for (session : WsClient in sessions) {
            if (session.type == "project" && session.id == sessionId) {
                session.session.sendMessage(updateEvent.getTextMessage())
            }
        }
    }

    fun getSessionById(sessionId: String): WsClient? {
        for (s: WsClient in sessions) {
            if (s.session.id == sessionId) {
                return s
            }
        }
        return null
    }

    fun removeSession(session: WebSocketSession): Unit {
        val objToRemove: WsClient? = getSessionById(session.id)
        sessions.remove(objToRemove)
        println("removeing ${session.id}")
    }

    @Throws(IOException::class)
    override fun afterConnectionEstablished(session: WebSocketSession) {
        super.afterConnectionEstablished(session)
        val arguments: List<String> = session.uri.toString().split("/")
        sessions.add(WsClient(arguments[4], arguments[5], session))
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        super.afterConnectionClosed(session, status)
        removeSession(session)
    }

    @Throws(IOException::class)
    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        println("New Text Message Received")
        session.sendMessage(message)
    }

    @Throws(IOException::class)
    override fun handleBinaryMessage(session: WebSocketSession, message: BinaryMessage) {
        println("New Binary Message Received")
        session.sendMessage(message)
    }
}
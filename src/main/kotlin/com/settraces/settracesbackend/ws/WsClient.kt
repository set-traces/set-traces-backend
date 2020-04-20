package com.settraces.settracesbackend.ws

import org.springframework.web.socket.WebSocketSession

class WsClient(val type: String, val id: String, val session: WebSocketSession) {
}
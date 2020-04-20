package com.settraces.settracesbackend.ws

//import org.springframework.http.server.ServerHttpRequest
//import org.springframework.http.server.ServerHttpResponse
//import org.springframework.web.socket.WebSocketExtension
//import org.springframework.web.socket.WebSocketHandler
//import org.springframework.web.socket.server.HandshakeHandler
//import org.springframework.web.socket.server.support.DefaultHandshakeHandler
//import java.util.jar.Attributes
//
//class HandShakeHandler : DefaultHandshakeHandler() {
//    fun beforeHandshake(request: ServerHttpRequest, response: ServerHttpResponse, webSocketHandler: WebSocketHandler, attributes: Attributes): Boolean {
//        println("before handshake")
//        return true
//    }
//
//    override fun filterRequestedExtensions(request: ServerHttpRequest, requestedExtensions: MutableList<WebSocketExtension>, supportedExtensions: MutableList<WebSocketExtension>): MutableList<WebSocketExtension> {
//        return super.filterRequestedExtensions(request, requestedExtensions, supportedExtensions)
//        println("this got called woho")
//    }
//}
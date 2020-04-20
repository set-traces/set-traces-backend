package com.settraces.settracesbackend.ws

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.http.server.ServletServerHttpRequest
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean
import org.springframework.web.socket.server.support.DefaultHandshakeHandler


@Configuration
@EnableWebSocket
class WebSocketConfig : WebSocketConfigurer {
    @Bean
    fun createWebSocketContainer(): ServletServerContainerFactoryBean {
        val container = ServletServerContainerFactoryBean()
        container.setMaxBinaryMessageBufferSize(1024000)
        return container
    }

    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(WebSocketHandler(), "/socket/*/*").setAllowedOrigins("*")
    }
}


package com.settraces.settracesbackend.ws

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*


@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("testws")
class FloodController {

    @Autowired
    private val msgTemplate: SimpMessagingTemplate? = null

    @GetMapping("/test")
    fun test() : String {
        msgTemplate!!.convertAndSend("/topic/messages",OutputMessage("admin", "This shit is working", Date().toString()))
        println("test")
        return "yes"
    }

}


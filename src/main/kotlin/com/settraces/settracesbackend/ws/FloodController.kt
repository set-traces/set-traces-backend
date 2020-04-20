package com.settraces.settracesbackend.ws

import com.settraces.settracesbackend.project.wsevents.ProjectEvent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*


@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("testws")
class FloodController {

    @Autowired
    val webSocketHandler: WebSocketHandler? = null

    @PostMapping("/test")
    fun test() : String {
        webSocketHandler!!.sendToProject("jake", ProjectEvent(Events.CHANGE_NAME, "jake", "Set Traces"))
        return "yes"
    }

    @GetMapping("/test")
    fun t() : List<String> {
        val list: ArrayList<String> = arrayListOf()
        for (s: WsClient in WebSocketHandler.sessions) {
            list.add(s.id)
        }
        return list
    }

}


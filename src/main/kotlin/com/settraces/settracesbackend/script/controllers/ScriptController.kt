package com.settraces.settracesbackend.script.controllers

import com.settraces.settracesbackend.project.models.Script
import com.settraces.settracesbackend.script.repository.ScriptRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/script")
class ScriptController {
    @Autowired
    val scriptRepository: ScriptRepository? = null
}
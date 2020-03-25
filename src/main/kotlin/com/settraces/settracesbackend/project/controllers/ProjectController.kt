package com.settraces.settracesbackend.project.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider
import com.settraces.settracesbackend.project.databasehandlers.ProjectDb
import com.settraces.settracesbackend.project.models.Project
import com.settraces.settracesbackend.project.models.Script
import com.settraces.settracesbackend.project.payload.resposne.ScriptResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/project")
class ProjectController {

    @Autowired
    val projectDb: ProjectDb? = null

    @GetMapping("/")
    fun getAll(): List<Project> {
        return projectDb!!.getAll()
    }

    @GetMapping("/{projectId}/script/{scriptId}")
    fun get(@PathVariable(value = "projectId") projectId: String, @PathVariable(value="scriptId") scriptId: String): Any? {

//        var script: Script = Script("", "name", "desc", "typ", "", "")
        var script = projectDb!!.getScriptById(projectId, scriptId)
        return ScriptResponse(script!!)

    }

}
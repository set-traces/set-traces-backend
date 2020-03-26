package com.settraces.settracesbackend.project.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider
import com.settraces.settracesbackend.project.databasehandlers.ProjectDb
import com.settraces.settracesbackend.project.models.Project
import com.settraces.settracesbackend.project.models.Script
import com.settraces.settracesbackend.project.payload.request.NewScriptRequest
import com.settraces.settracesbackend.project.payload.resposne.CreateScriptResponse
import com.settraces.settracesbackend.project.payload.resposne.ScriptResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/project")
class ProjectController {

    @Autowired
    val projectDb: ProjectDb? = null

    @GetMapping("/")
    fun getAll(): List<Project> {
        return projectDb!!.getAll()
    }

    @PostMapping("/{projectId}/script/")
    fun insertScript(@PathVariable(value="projectId") projectId: String, @RequestBody newScriptRequest: @Valid NewScriptRequest): CreateScriptResponse {
        return try {
            projectDb!!.newScript(newScriptRequest)
            CreateScriptResponse(true)
        } catch(e: Exception) {
            println(e)
            CreateScriptResponse(false)
        }
    }

    @GetMapping("/{projectId}/script/{scriptId}")
    fun get(@PathVariable(value = "projectId") projectId: String, @PathVariable(value="scriptId") scriptId: String): Any? {
//        var script: Script = Script("", "name", "desc", "typ", "", "")
        var script = projectDb!!.getScriptById(projectId, scriptId)
        return ScriptResponse(script!!)
    }

}
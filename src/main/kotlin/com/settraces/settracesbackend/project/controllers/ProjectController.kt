package com.settraces.settracesbackend.project.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider
import com.settraces.settracesbackend.project.databasehandlers.ProjectDb
import com.settraces.settracesbackend.project.databasehandlers.ScriptDb
import com.settraces.settracesbackend.project.databasehandlers.ScriptTypeDb
import com.settraces.settracesbackend.project.models.Project
import com.settraces.settracesbackend.project.models.RoleMeta
import com.settraces.settracesbackend.project.models.Script
import com.settraces.settracesbackend.project.models.ScriptType
import com.settraces.settracesbackend.project.payload.request.NewProjectRequest
import com.settraces.settracesbackend.project.payload.request.NewScriptRequest
import com.settraces.settracesbackend.project.payload.request.NewScriptRoleRequest
import com.settraces.settracesbackend.project.payload.request.NewScriptTypeRequest
import com.settraces.settracesbackend.project.payload.resposne.CreateScriptResponse
import com.settraces.settracesbackend.project.payload.resposne.ScriptResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("api/project")
class ProjectController {

    @Autowired
    val projectDb: ProjectDb? = null

    @Autowired
    val scriptTypeDb: ScriptTypeDb? = null

    @Autowired
    val scriptDb: ScriptDb? = null

    @GetMapping("/")
    fun getAll(): List<Project> {
        return projectDb!!.getAll()
    }

    @PostMapping("")
    fun newProject(@RequestBody newProjectRequest: @Valid NewProjectRequest): Project? {
        val project: Project = Project(newProjectRequest.name, newProjectRequest.description)
        return projectDb!!.create(project)
    }

    @PostMapping("/{projectId}/type/")
    fun insertScriptType(@PathVariable(value="projectId") projectId: String, @RequestBody newScriptTypeRequest: NewScriptTypeRequest): ScriptType? {
        val scriptType: ScriptType = ScriptType(newScriptTypeRequest.name, projectId)
        return scriptTypeDb!!.create(scriptType)
    }

    @PostMapping("/{projectId}/script/")
    fun insertScript(@PathVariable(value="projectId") projectId: String, @RequestBody newScriptRequest: @Valid NewScriptRequest): Any? {
        return try {
            // id: String, name: String, description: String, type: String, projectId: String, scriptTypeId: String) {
            val script = Script(newScriptRequest.name, newScriptRequest.description, "", projectId, newScriptRequest.typeId)
            return projectDb!!.newScript(script)
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

    @PostMapping("/{projectId}/script/{scriptId}/role/")
    fun newRole(@PathVariable(value="projectId") projectId: String, @PathVariable(value="scriptId") scriptId: String, @RequestBody newScriptRoleRequest: @Valid NewScriptRoleRequest): RoleMeta {
        var desc = newScriptRoleRequest.description
        if (desc == null) {
            desc = ""
        }
        return scriptDb!!.newRole(RoleMeta(newScriptRoleRequest.role, desc, newScriptRoleRequest.actorId, scriptId, ""))

    }

}
package com.settraces.settracesbackend.project.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider
import com.settraces.settracesbackend.project.databasehandlers.ProjectDb
import com.settraces.settracesbackend.project.databasehandlers.ScriptDb
import com.settraces.settracesbackend.project.databasehandlers.ScriptTypeDb
import com.settraces.settracesbackend.project.models.*
import com.settraces.settracesbackend.project.payload.request.*
import com.settraces.settracesbackend.project.payload.resposne.CreateScriptResponse
import com.settraces.settracesbackend.project.payload.resposne.ScriptResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.NotBlank


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

    /**
     * @api {get} /project/ Get all projects
     * @apiName All Projects
     * @apiGroup project
     */
    @GetMapping("/")
    fun getAll(): List<Project> {
        return projectDb!!.getAll()
    }

    /**
     * @api {get} /project/{{projectId}} Get project by id
     * @apiName Project
     * @apiGroup project
     */
    @GetMapping("/{projectId}")
    fun getProject(@PathVariable projectId: @NotBlank String): Project? {
        return projectDb!!.getProjectById(projectId)
    }


    /**
     * @api {post} /project/ Create new project
     * @apiName Create project
     * @apiGroup project
     *
     * @apiParam {String} name
     * @apiParam {String} description
     */
    @PostMapping("")
    fun newProject(@RequestBody newProjectRequest: @Valid NewProjectRequest): Project? {
        val project: Project = Project(newProjectRequest.name, newProjectRequest.description)
        val result: Project? = projectDb!!.create(project)
        val newScriptType: ScriptType = ScriptType("Sketch", result!!.id)
        scriptTypeDb!!.create(newScriptType)
        return result
    }

    /**
     * @api {put} /{projectId}/script/{scriptId}/name Change name of script
     * @apiName Change name of script
     * @apiParam {String} name
     * @apiGroup script
     */
    @PutMapping("/{projectId}/script/{scriptId}/name")
    fun changeName(@PathVariable projectId: @NotBlank String, @PathVariable scriptId: @NotBlank String, @RequestBody changeScriptNameRequest: @Valid ChangeScriptNameRequest): Boolean {
        scriptDb!!.updateNameForScript(projectId, scriptId, changeScriptNameRequest.name)
        return true
    }

    /**
     * @api {put} /{projectId}/script/{scriptId}/description Change description of script
     * @apiName Change description of script
     * @apiParam {String} description
     * @apiGroup script
     */
    @PutMapping("/{projectId}/script/{scriptId}/description")
    fun changeDesc(@PathVariable projectId: @NotBlank String, @PathVariable scriptId: @NotBlank String, @RequestBody changeScriptDescRequest: @Valid ChangeScriptDescRequest): Boolean {
        println("saving desc")
        scriptDb!!.updateDescriptionForScript(projectId, scriptId, changeScriptDescRequest.description)
        return true
    }

    /**
     * @api {post} /project/{projectId}/type/ Create new script type (sketch, song, etc)
     * @apiName Create new script type (sketch, song, etc)
     * @apiGroup project
     *
     * @apiParam {String} name
     */
    @PostMapping("/{projectId}/type/")
    fun insertScriptType(@PathVariable(value="projectId") projectId: String, @RequestBody newScriptTypeRequest: NewScriptTypeRequest): ScriptType? {
        val scriptType: ScriptType = ScriptType(newScriptTypeRequest.name, projectId)
        return scriptTypeDb!!.create(scriptType)
    }

    /**
     * @api {post} /project/{{projectId}}/script/ Create new script
     * @apiName Create new script
     * @apiGroup script
     *
     * @apiParam {String} name Name of script
     * @apiParam {String} typeId Id of type (id of sketch, song, etc)
     * @apiParam {String} description Description of script
     */
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

    /**
     * @api {get} /project/{{projectId}}/script/{{scriptId}} Get script by id
     * @apiName Get all scripts
     * @apiGroup script
     */
    @GetMapping("/{projectId}/script/{scriptId}/")
    fun get(@PathVariable(value = "projectId") projectId: String, @PathVariable(value="scriptId") scriptId: String): Any? {
//        var script: Script = Script("", "name", "desc", "typ", "", "")
        var script = projectDb!!.getScriptById(projectId, scriptId)
        return ScriptResponse(script!!)
    }

    /**
     * @api {post} /project/{{projectId}}/script/{{scriptId}}/role/ Create new role in script
     * @apiName Create new role for script
     * @apiGroup script
     *
     * @apiParam {String} role Name of role
     * @apiParam {String} [actorId] Id of the actor
     * @apiParam {String} [description] Describe the role (costumes and so on)
     */
    @PostMapping("/{projectId}/script/{scriptId}/role/")
    fun newRole(@PathVariable(value="projectId") projectId: String, @PathVariable(value="scriptId") scriptId: String, @RequestBody newScriptRoleRequest: @Valid NewScriptRoleRequest): RoleMeta {
        var desc = newScriptRoleRequest.description
        if (desc == null) {
            desc = ""
        }

        return scriptDb!!.newRole(RoleMeta(newScriptRoleRequest.role, desc, newScriptRoleRequest.actorId, scriptId, "", ""))
    }

    /**
     * @api {post} /project/{{projectId}}/script/{{scriptId}}/line/  Create new line in script
     * @apiName Create new line in script
     * @apiGroup script
     *
     * @apiParam {String} type Type of line - REMARK or ACTION
     * @apiParam {String} text What's being said
     * @apiParam {String} [roleId] Id of the role performing the line
     * @apiParam {Integer} [ordering] Placement of the line, if unset, added to end of script
     */
    @PostMapping("/{projectId}/script/{scriptId}/line/")
    fun newRole(@PathVariable(value="projectId") projectId: String, @PathVariable(value="scriptId") scriptId: String,
    @RequestBody newLineRequest: @Valid NewLineRequest): Line? {
        val inputLine: Line = Line(newLineRequest.type, newLineRequest.text, newLineRequest.roleId, scriptId, newLineRequest.ordering)
        return scriptDb!!.newLine(inputLine)
    }

}
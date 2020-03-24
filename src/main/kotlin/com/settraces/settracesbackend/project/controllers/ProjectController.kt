package com.settraces.settracesbackend.project.controllers


import com.settraces.settracesbackend.response.MessageResponse
import com.settraces.settracesbackend.authentication.repository.RoleRepository
import com.settraces.settracesbackend.authentication.repository.UserRepository
import com.settraces.settracesbackend.authentication.security.jwt.JwtUtils
import com.settraces.settracesbackend.project.models.Project
import com.settraces.settracesbackend.project.payload.requests.NewProjectRequest
import com.settraces.settracesbackend.project.payload.response.ProjectsResponse
import com.settraces.settracesbackend.project.repository.ProjectRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import java.util.*


@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController("Project")
@RequestMapping("/api/project")
class ProjectController {
    @Autowired
    var authenticationManager: AuthenticationManager? = null
    @Autowired
    var userRepository: UserRepository? = null
    @Autowired
    var roleRepository: RoleRepository? = null
    @Autowired
    var encoder: PasswordEncoder? = null
    @Autowired
    var jwtUtils: JwtUtils? = null
    @Autowired
    var projectRepository: ProjectRepository? = null


    @GetMapping("")
    fun allProjects(): ResponseEntity<*> {
        var projects: MutableList<Project?> = projectRepository!!.findAll();
        return ResponseEntity.ok<Any>(ProjectsResponse(projects))
    }

    @PostMapping("/")
    fun newProject(@RequestBody newProjectRequest: NewProjectRequest): ResponseEntity<*> {
        if (newProjectRequest != null) {
            val project: Project = Project(newProjectRequest.name!!, newProjectRequest.description!!)
            projectRepository!!.save(project);
            return ResponseEntity.ok<Any>(MessageResponse("Project created"))
        } else {
            return ResponseEntity.ok<Any>(MessageResponse("Error"))
        }
    }

    @GetMapping("/{id}")
    fun getProject(@PathVariable id: UUID) : Any? {
        return try {
            val project: Project? = projectRepository!!.findAllById(id);
            project
        } catch (e: Exception) {
            return MessageResponse("Could not find project")
        }
    }
}


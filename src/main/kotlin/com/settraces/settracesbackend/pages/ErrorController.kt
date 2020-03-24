package com.settraces.settracesbackend.pages

import com.settraces.settracesbackend.response.MessageResponse
import com.settraces.settracesbackend.authentication.repository.RoleRepository
import com.settraces.settracesbackend.authentication.repository.UserRepository
import com.settraces.settracesbackend.authentication.security.jwt.JwtUtils
import com.settraces.settracesbackend.project.models.Project
import com.settraces.settracesbackend.project.payload.requests.NewProjectRequest
import com.settraces.settracesbackend.project.payload.response.ProjectsResponse
import com.settraces.settracesbackend.project.repository.ProjectRepository
import org.aspectj.bridge.Message
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import java.util.*


@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController("Error")
class ErrorController {

    @GetMapping("/error")
    fun error(): MessageResponse {
        return MessageResponse("An error occured")
    }
}


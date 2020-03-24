package com.settraces.settracesbackend.authentication.controllers

import com.settraces.settracesbackend.authentication.models.ERole
import com.settraces.settracesbackend.authentication.models.Role
import com.settraces.settracesbackend.authentication.models.User
import com.settraces.settracesbackend.authentication.payload.request.LoginRequest
import com.settraces.settracesbackend.authentication.payload.request.SignupRequest
import com.settraces.settracesbackend.authentication.payload.response.JwtResponse
import com.settraces.settracesbackend.response.MessageResponse
import com.settraces.settracesbackend.authentication.repository.RoleRepository
import com.settraces.settracesbackend.authentication.repository.UserRepository
import com.settraces.settracesbackend.authentication.security.jwt.JwtUtils
import com.settraces.settracesbackend.authentication.services.UserDetailsImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import java.util.function.Supplier
import java.util.stream.Collectors
import javax.validation.Valid


@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/auth/api")
class AuthController {
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

    @PostMapping("/signin")
    fun authenticateUser(@RequestBody loginRequest: @Valid LoginRequest?): ResponseEntity<*> {
        val authentication = authenticationManager!!.authenticate(
                UsernamePasswordAuthenticationToken(loginRequest!!.username, loginRequest.password))
        SecurityContextHolder.getContext().authentication = authentication
        val jwt = jwtUtils!!.generateJwtToken(authentication)
        val userDetails = authentication.principal as UserDetailsImpl
        val roles = userDetails.authorities.stream()
                .map { item: SimpleGrantedAuthority -> item.authority }
                .collect(Collectors.toList())
        return ResponseEntity.ok<Any>(JwtResponse(jwt,
                userDetails.id!!,
                userDetails.username,
                userDetails.getEmail(),
                roles))
    }

    @PostMapping("/signup")
    fun registerUser(@RequestBody signUpRequest: @Valid SignupRequest?): ResponseEntity<*> {
        if (signUpRequest != null) {
            println(signUpRequest.email);
            if (userRepository!!.existsByUsername(signUpRequest.username)!!) {
                return ResponseEntity
                        .badRequest()
                        .body<Any>(MessageResponse("Error: Username is already taken!"))
            }
        }
        if (userRepository!!.existsByEmail(signUpRequest?.email)!!) {
            return ResponseEntity
                    .badRequest()
                    .body<Any>(MessageResponse("Error: Email is already in use!"))
        }
        // Create new user's account
        var user: User? = null;
        if (signUpRequest != null) {
            user = User(signUpRequest.username,
            signUpRequest.email,
            encoder!!.encode(signUpRequest.password))
        } else {
            throw Exception("Missing data");
        }
        val strRoles: Set<String>? = signUpRequest.role
        val roles: MutableSet<Role> = HashSet<Role>()
        if (strRoles == null) {
            val userRole: Role = roleRepository!!.findByName(ERole.ROLE_USER)
                    ?.orElseThrow(Supplier { RuntimeException("Error: Role is not found.") })!!
            roles.add(userRole)
        } else {
            strRoles.forEach() { role: String ->
                when (role) {
                    "admin" -> {
                        val adminRole: Role = roleRepository!!.findByName(ERole.ROLE_ADMIN)
                                ?.orElseThrow(Supplier { RuntimeException("Error: Role is not found.") })!!
                        roles.add(adminRole)
                    }
                    "mod" -> {
                        val modRole: Role = roleRepository!!.findByName(ERole.ROLE_MODERATOR)
                                ?.orElseThrow(Supplier { RuntimeException("Error: Role is not found.") })!!
                        roles.add(modRole)
                    }
                    else -> {
                        val userRole: Role = roleRepository!!.findByName(ERole.ROLE_USER)
                                ?.orElseThrow(Supplier { RuntimeException("Error: Role is not found.") })!!
                        roles.add(userRole)
                    }
                }
                Unit
            }
        }
        user.roles = roles
        userRepository!!.save(user)
        return ResponseEntity.ok<Any>(MessageResponse("User registered successfully!"))
    }
}


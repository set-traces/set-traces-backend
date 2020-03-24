package com.settraces.settracesbackend.profile.controllers

import com.settraces.settracesbackend.authentication.models.User
import com.settraces.settracesbackend.authentication.repository.UserRepository
import com.settraces.settracesbackend.authentication.security.IsLoggedIn
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import kotlin.collections.HashSet


@RestController
@RequestMapping("/api/profile")
class ProfileController {

    @Autowired
    val userRepository: UserRepository? = null

    @GetMapping("")
    @IsLoggedIn
    fun getProfile(): Any? {
        val securityContext = SecurityContextHolder.getContext()
        val userName: String = securityContext.authentication.name
        return try {
            val user: User? = userRepository!!.findByUsername(userName)?.orElseThrow { UsernameNotFoundException("User Not Found with username: $userName")}
            user
        } catch (e: Exception) {
            val dat: HashMap<String, String> = HashMap<String, String>();
            dat.put("err", "User not found")
            dat
        }

    }
}
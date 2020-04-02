package com.settraces.settracesbackend.devops

import com.settraces.settracesbackend.profile.controllers.Test
import com.settraces.settracesbackend.profile.controllers.TestMapper

import com.settraces.settracesbackend.auth.databasehandlers.UserDb
import com.settraces.settracesbackend.auth.models.User
import com.settraces.settracesbackend.devops.payload.response.VersionResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin(origins = ["*"], maxAge = 3600)
@RequestMapping("/api")
class DevOpsController {

    /**
     * @api {get} /version Version
     * @apiName Version
     * @apiGroup devops
     */
    @GetMapping("/version")
    fun getVersion(): VersionResponse {
        return VersionResponse(1, 2, 0)
    }
}
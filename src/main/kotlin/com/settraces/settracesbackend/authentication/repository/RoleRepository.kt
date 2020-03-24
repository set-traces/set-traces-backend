package com.settraces.settracesbackend.authentication.repository

import com.settraces.settracesbackend.authentication.models.ERole
import com.settraces.settracesbackend.authentication.models.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface RoleRepository : JpaRepository<Role?, Long?> {
    fun findByName(name: ERole?): Optional<Role?>?
}
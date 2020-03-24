package com.settraces.settracesbackend.repository

import com.settraces.settracesbackend.models.ERole
import com.settraces.settracesbackend.models.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface RoleRepository : JpaRepository<Role?, Long?> {
    fun findByName(name: ERole?): Optional<Role?>?
}
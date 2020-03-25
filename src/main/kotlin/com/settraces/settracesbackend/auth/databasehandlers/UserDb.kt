package com.settraces.settracesbackend.auth.databasehandlers

import com.settraces.settracesbackend.auth.models.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component


class UserDb {

    @Autowired
    val jdbcTemplate: JdbcTemplate? = null

    fun getAll(): List<User> {
        println(jdbcTemplate)
//            return try {
//                val users: List<User> = jdbcTemplate!!.query("select * from users", UserMapper()) // List<User>
//                println(users)
//                users
//            } catch (e: SQLException) {
//                ArrayList() // empty if user is empty
//            }
        return ArrayList()
    }

}
package com.settraces.settracesbackend.profile.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.sql.ResultSet
import java.sql.SQLException
import java.util.function.Consumer

@RestController
@RequestMapping("/test")
class BasicController {

    @Autowired
    val jdbcTemplate: JdbcTemplate? = null

    @GetMapping("")
    fun test(): String {

        return try {
            val tests: Test? = jdbcTemplate!!.queryForObject("select * from test", TestMapper())
            tests!!.name
        } catch(e: Exception) {
            println(e)
            "på tide å legge seg"
        }
//        try {
//            jdbcTemplate!!.query<Any>(
//                    "SELECT * from users where id!=?", arrayOf<Any>("Josh")
//            ) { rs: ResultSet, rowNum: Int -> println("got a user") }
//        } catch(e: Exception) {
//            println(e)
//            println("got an exception")
//        }
    }
}
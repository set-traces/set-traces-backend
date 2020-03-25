package com.settraces.settracesbackend.database

import com.settraces.settracesbackend.database.SpringJdbcConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.io.File
import java.lang.Exception
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Statement
import java.util.*
import javax.sql.DataSource


@Component
class Migrate {

    fun readDir(): List<String> {
        val files: ArrayList<String> = ArrayList()
        File("src/main/kotlin/com/settraces/settracesbackend/database/migrations/").walk().forEach{
            try {
                var fileName: String = it.toString().split("src/main/kotlin/com/settraces/settracesbackend/database/migrations/")[1]
                if (!fileName.equals("setup.sql")) {
                    files.add(fileName)
                }
            } catch(e: Exception) {
                // only root folder
            }
        }
        files.sort()
        return files
    }

    fun readFile(fileName: String): List<String> {
        var statements: ArrayList<String> = ArrayList()
        var statement = ""
        File("src/main/kotlin/com/settraces/settracesbackend/database/migrations/$fileName").forEachLine {
            statement += it.trimIndent()
            if (";" in it) {
                statements.add(statement)
                statement = ""
            }
        }
        return statements
    }

    fun call(sql: String) {
        val config: SpringJdbcConfig = SpringJdbcConfig()
        val datasource: DataSource = config.mysqlDataSource()
        val conn: Connection = datasource.connection
        val statement: PreparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        statement.execute()
    }


    fun getMigrations(): List<String> {
        val config: SpringJdbcConfig = SpringJdbcConfig()
        val datasource: DataSource = config.mysqlDataSource()
        val conn: Connection = datasource.connection
        val sql: String = "select * from migrations"
        val statement: PreparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        statement.execute()
        val rs: ResultSet = statement.resultSet
        var files: ArrayList<String> = arrayListOf()
        while (rs.next()) {
            files.add(rs.getString("file"))
        }
        return files
    }

    fun insertMigrations(): Unit {
        var statements: List<String> = readFile("setup.sql")
        statements.forEach {call(it)}
    }

    fun makeMigration(fileName: String) {
        println("Migrating $fileName")
        var statements: List<String> = readFile(fileName)
        statements.forEach {
            call(it)
        }
        call("insert into migrations(file) values ('$fileName')")
    }

    fun migrate() {
        println("starting database migrations")
        try {
            var newMigrations: List<String> = readDir()
            var migrations: List<String>? = null
            var actualMigrations: ArrayList<String> = arrayListOf()
            try {
                 migrations = getMigrations()
            } catch (e: Exception) {
                println("setting up new database")
                insertMigrations()
                migrations = getMigrations()
            }
            newMigrations.forEach {if (!migrations!!.contains(it)) {
                actualMigrations.add(it)
            } }
            actualMigrations.forEach {makeMigration(it)}
            if (actualMigrations.size == 0) {
                println("No migrations detected")
            } else {
                println("database migrated successfully")
            }
            println("Application ready")
        } catch (e: Exception) {
            println("migrations failed due to:")
            println(e)
        }
    }
}


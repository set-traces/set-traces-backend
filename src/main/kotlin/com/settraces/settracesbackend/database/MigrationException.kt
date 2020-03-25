package com.settraces.settracesbackend.database

class MigrationException(err: String, e: Exception) : Exception() {
    init {
        println(err)
        println("due to")
        println(e)
    }
}
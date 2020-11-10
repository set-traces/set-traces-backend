package com.settraces.settracesbackend.database

class DataBaseNotConnectedException : Exception {
    constructor(error: String) {
        println(error)
    }
}
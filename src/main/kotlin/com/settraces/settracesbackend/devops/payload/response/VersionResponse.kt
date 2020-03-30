package com.settraces.settracesbackend.devops.payload.response

class VersionResponse(var version: String) {
    constructor(o: Int, t: Int, th: Int) : this("$o.$t.$th") {
    }
}
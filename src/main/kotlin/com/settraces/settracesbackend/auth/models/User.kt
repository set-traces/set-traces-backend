package com.settraces.settracesbackend.auth.models

import org.springframework.data.annotation.Id


class User {
    @Id
    var id: String? = null
    var username: String? = null
    var email: String? = null
    var password: String? = null

    constructor(id: String?, username: String?, email: String?, password: String?) {
        this.id = id
        this.username = username
        this.email = email
        this.password = password
    }

    constructor(id: String?, username: String?, email: String?) {
        this.id = id
        this.username = username
        this.email = email
    }
}

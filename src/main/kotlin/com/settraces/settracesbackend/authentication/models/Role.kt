package com.settraces.settracesbackend.authentication.models

import javax.persistence.*

@Entity
@Table(name = "roles")
class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    var name: ERole? = null

    constructor() {}
    constructor(name: ERole?) {
        this.name = name
    }

}
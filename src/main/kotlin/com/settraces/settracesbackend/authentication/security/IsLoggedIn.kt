package com.settraces.settracesbackend.authentication.security

import org.springframework.security.access.prepost.PreAuthorize


@PreAuthorize("hasRole('USER')")
annotation class IsLoggedIn {

}

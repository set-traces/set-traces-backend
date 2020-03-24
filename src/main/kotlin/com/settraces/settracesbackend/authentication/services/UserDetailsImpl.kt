package com.settraces.settracesbackend.services

import com.settraces.settracesbackend.models.Role
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.settraces.settracesbackend.models.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

class UserDetailsImpl(
        var id: String?,
        private var username: @NotBlank @Size(max = 20) String?,
        private var password: @NotBlank @Size(max = 120) String?,
        private var email: @NotBlank @Size(max = 50) @Email String?,
        private val _authorities: List<SimpleGrantedAuthority>
) : UserDetails {
    constructor() : this(
            "",
            "missinguser",
            "",
            "missing@settraces.com",
            ArrayList()
    ) {
        println("there was an error");
    }

    companion object {
        @JvmStatic
        fun build(user: User): UserDetailsImpl {
            var roles:Set<Role> = user.roles;
            var auths: List<SimpleGrantedAuthority> = roles.map {SimpleGrantedAuthority(it.name?.name)};
            return UserDetailsImpl(user.id.toString(), user.username, user.password, user.email, auths);
        }
    }

    fun getEmail(): String {
        return email.toString();
    }

    override fun getAuthorities(): List<SimpleGrantedAuthority> {
        return _authorities;
    }

    override fun isEnabled(): Boolean {
        return true;
    }

    override fun getUsername(): String {
        return this.username.toString()
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true;
    }

    override fun getPassword(): String {
        return this.password.toString();
    }

    override fun isAccountNonExpired(): Boolean {
        return true;
    }

    override fun isAccountNonLocked(): Boolean {
        return true;
    }

}
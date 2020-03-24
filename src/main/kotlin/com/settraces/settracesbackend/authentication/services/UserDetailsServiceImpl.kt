package com.settraces.settracesbackend.authentication.services


import com.settraces.settracesbackend.authentication.models.User
import com.settraces.settracesbackend.authentication.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class UserDetailsServiceImpl : UserDetailsService {

    @Autowired
    var userRepository: UserRepository? = null

    @Transactional
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val user: User? = userRepository!!.findByUsername(username)
                ?.orElseThrow { UsernameNotFoundException("User Not Found with username: $username") }
        if (user != null) {
            return UserDetailsImpl.build(user)
        }
        else {
            return UserDetailsImpl()
        }
    }
}
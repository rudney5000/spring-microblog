package com.dedyrudney.microblog.repository

import com.dedyrudney.microblog.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UserRepository: JpaRepository<User, Long> {
    fun findByUsername(username: String): Optional<User>
}
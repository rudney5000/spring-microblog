package com.dedyrudney.microblog.service

import com.dedyrudney.microblog.dto.UserConnexion
import com.dedyrudney.microblog.entity.User
import com.dedyrudney.microblog.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Optional

interface UserService {
    fun getAllUsers(): List<User>

    fun getOneUser(id: Long): Optional<User>

    fun saveUser(user: User): User

    fun updateUser(id: Long, userInput: User): Optional<User>

    fun deleteUser(id: Long)

    fun login(userData: UserConnexion): User
}
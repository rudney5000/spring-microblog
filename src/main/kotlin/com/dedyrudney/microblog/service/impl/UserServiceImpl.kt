package com.dedyrudney.microblog.service.impl

import com.dedyrudney.microblog.dto.UserConnexion
import com.dedyrudney.microblog.entity.User
import com.dedyrudney.microblog.repository.UserRepository
import com.dedyrudney.microblog.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class UserServiceImpl @Autowired private constructor(private var userRepository: UserRepository): UserService {

    override fun getAllUsers(): List<User> = userRepository.findAll()

    override fun getOneUser(id: Long): Optional<User> = userRepository.findById(id)

    override fun saveUser(user: User): User = userRepository.save(user)

    override fun updateUser(id: Long, userInput: User): Optional<User> {
        val optional = getOneUser(id)
        if (optional.isPresent) Optional.empty<User>()

        return optional.map {
            val userFound = it.copy(
                username = userInput.username,
                fullname = userInput.fullname,
                pays = userInput.pays,
                profession = userInput.profession,
                ville = userInput.ville,
                posts = userInput.posts,
                password = userInput.password
            )
            userRepository.save(userFound)
        }
    }

    override fun deleteUser(id: Long) {
        userRepository.findById(id).map {
            userRepository.delete(it)
        }.orElseThrow {
            throw RuntimeException("Id $id not found")
        }
    }

    override fun login(userData: UserConnexion): User {
        val user = userRepository.findByUsername(userData.username).orElseThrow{IllegalArgumentException("User not Found")}
        if (user.password == userData.password && user.email == userData.email){
            return user
        }
        throw IllegalArgumentException("username or password or email not correct")
    }
}


package com.dedyrudney.microblog.service

import com.dedyrudney.microblog.entity.User
import com.dedyrudney.microblog.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService @Autowired private constructor(private var userRepository: UserRepository){
    fun getUsers(): List<User> = userRepository.findAll()

    fun getOne(id: Long): User = userRepository
        .findById(id)
        .orElseThrow{
            IllegalArgumentException("User with this $id was not found")
        }

    fun saveUser(user: User): User = userRepository.save(user)

    fun updateUser(id: Long, userInput: User): User {
        val userFound = getOne(id)

        userFound.username = userInput.username
        userFound.fullname = userInput.fullname
        userFound.pays = userInput.pays
        userFound.profession = userInput.profession
        userFound.ville = userInput.ville
        userFound.posts = userInput.posts
        userFound.password = userInput.password

        return userRepository.save(userFound)
    }

    fun deleteUser(id: Long): String{
        val userFound = getOne(id)

        userRepository.delete(userFound)
        return "User deleted"
    }
}
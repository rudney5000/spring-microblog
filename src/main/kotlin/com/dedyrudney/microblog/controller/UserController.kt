package com.dedyrudney.microblog.controller

import com.dedyrudney.microblog.dto.UserConnexion
import com.dedyrudney.microblog.entity.User
import com.dedyrudney.microblog.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class UserController @Autowired constructor(private val userService: UserService) {

    @GetMapping("/users")
    fun getUserList(): List<User> = userService.getUsers()

    @GetMapping("/users/{id}")
    fun getUser(@PathVariable id: Long): User = userService.getOne(id)

    @PostMapping("/login")
    fun login(@RequestBody @Valid userData: UserConnexion): User = userService.login(userData)

    @PostMapping("/users")
    fun createUser(@RequestBody user: User): User = userService.saveUser(user)

    @PutMapping("/users/{id}")
    fun updateUser(@PathVariable id: Long,@RequestBody user: User): User = userService.updateUser(id, user)

    @DeleteMapping("/users/{id}")
    fun deleteUser(@PathVariable id: Long): String = userService.deleteUser(id)
}
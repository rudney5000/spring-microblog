package com.dedyrudney.microblog.controller

import com.dedyrudney.microblog.dto.UserConnexion
import com.dedyrudney.microblog.dto.UserDto
import com.dedyrudney.microblog.entity.User
import com.dedyrudney.microblog.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RequestMapping("/api/users")
@RestController
class UserController @Autowired constructor(private val userService: UserService) {

    @GetMapping("/All")
    fun getAllUser(): List<User> = userService.getAllUsers()

    @GetMapping("/{id}")
    fun getOneUser(@PathVariable id: Long): ResponseEntity<User>{
         return userService.getOneUser(id).map {
             ResponseEntity.ok(it)
         }.orElse(ResponseEntity.notFound().build())
    }

    @PostMapping("/login")
    fun login(@RequestBody @Valid userData: UserConnexion): User = userService.login(userData)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@RequestBody user: User): UserDto {
        val createUser = userService.saveUser(user)
        return UserDto(
            createUser.id,
            createUser.username,
            createUser.email,
            createUser.profession,
            createUser.fullname,
            createUser.pays,
            createUser.ville,
            createUser.posts
        )
    }

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Long,@RequestBody user: User): ResponseEntity<User> {
        return userService.updateUser(id, user).map {
            ResponseEntity.ok(it)
        }.orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<Void> {
        userService.deleteUser(id)
        return ResponseEntity<Void>(HttpStatus.OK)
    }
}
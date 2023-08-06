package com.dedyrudney.microblog

import com.dedyrudney.microblog.controller.UserController
import com.dedyrudney.microblog.dto.UserConnexion
import com.dedyrudney.microblog.entity.User
import com.dedyrudney.microblog.service.UserService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.util.*

@SpringBootTest
@ExtendWith(MockitoExtension::class)
class UserControllerTest {

    private lateinit var mockMvc: MockMvc

    @Mock
    private lateinit var userService: UserService

    @BeforeEach
    fun setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(UserController(userService)).build()
    }
    @Test
    fun getAllUser_ShouldReturnUserList() {
        val userList = listOf(
            User(
                1,
                "John",
                "john@example.com",
                "Developer",
                "User",
                "User",
                "User",
                "Password"
            )
        )
        `when`(userService.getAllUsers()).thenReturn(userList)

        mockMvc.perform(get("/api/users/All"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].username").value("John"))
            .andExpect(jsonPath("$[0].email").value("john@example.com"))
            .andExpect(jsonPath("$[0].profession").value("Developer"))
            .andExpect(jsonPath("$[0].ville").value("User"))
            .andExpect(jsonPath("$[0].pays").value("User"))
            .andExpect(jsonPath("$[0].fullname").value("User"))
            .andExpect(jsonPath("$[0].password").value("Password"))

    }

    @Test
    fun getOneUser_ExistingUserId_ShouldReturnUser() {
        val user = User(
            1,
            "John",
            "john@example.com",
            "Developer",
            "User",
            "User",
            "User",
            "Password"
        )
        `when`(userService.getOneUser(anyLong())).thenReturn(Optional.of(user))

        mockMvc.perform(get("/api/users/1"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.username").value("John"))
            .andExpect(jsonPath("$.email").value("john@example.com"))
            .andExpect(jsonPath("$.profession").value("Developer"))
            .andExpect(jsonPath("$.ville").value("User"))
            .andExpect(jsonPath("$.pays").value("User"))
            .andExpect(jsonPath("$.fullname").value("User"))
            .andExpect(jsonPath("$.password").value("Password"))
    }

    @Test
    fun getOneUser_NonExistingUserId_ShouldReturnNotFound() {
        `when`(userService.getOneUser(anyLong())).thenReturn(Optional.empty())

        mockMvc.perform(get("/api/users/1"))
            .andExpect(status().isNotFound)
    }

    @Test
    fun login_ValidUserData_ShouldReturnUser() {
        val user = User(
            1,
            "John",
            "john@example.com",
            "Developer",
            "User",
            "User",
            "User",
            "Password"
        )
        val userData = UserConnexion("John", "password", "john@example.com")
        `when`(userService.login(userData)).thenReturn(user)

        mockMvc.perform(post("/api/users/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"username\":\"John\",\"email\":\"john@example.com\",\"password\":\"password\"}"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.username").value("John"))
            .andExpect(jsonPath("$.email").value("john@example.com"))
            .andExpect(jsonPath("$.profession").value("Developer"))
            .andExpect(jsonPath("$.ville").value("User"))
            .andExpect(jsonPath("$.pays").value("User"))
            .andExpect(jsonPath("$.fullname").value("User"))
            .andExpect(jsonPath("$.password").value("Password"))
    }

    @Test
    fun testDeleteUser() {
        val userId = 1L

        val response = mockMvc.perform(MockMvcRequestBuilders.delete("/api/users/{id}", userId))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn().response

        Mockito.verify(userService, Mockito.times(1)).deleteUser(userId)
    }


//    @Test
//    fun testUpdateUser() {
//        val userId = 1L
//        val updatedUser = User(
//            1,
//            "John",
//            "john@example.com",
//            "Developer",
//            "User",
//            "User",
//            "User",
//            "Password"
//        ).apply {
//            username = "Puser"
//            email = "newemail@example.com"
//        }
//
//        Mockito.`when`(userService.updateUser(eq(userId), Mockito.isNull())).thenReturn(Optional.ofNullable(updatedUser))
//
//        val response = mockMvc.perform(MockMvcRequestBuilders.put("/api/users/{id}", userId)
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(ObjectMapper().writeValueAsString(updatedUser)))
//            .andExpect(MockMvcResultMatchers.status().isOk)
//            .andReturn().response
//
//        Mockito.verify(userService, Mockito.times(1)).updateUser(eq(userId), any(User::class.java))
//    }
}
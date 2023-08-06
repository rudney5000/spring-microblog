package com.dedyrudney.microblog.dto

import com.dedyrudney.microblog.entity.User

data class PostDto(
    var id: Long?=null,
    var content: String,
    var userId: Long,
    val user : User?=null,
)
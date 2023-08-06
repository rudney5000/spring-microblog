package com.dedyrudney.microblog.dto

import com.dedyrudney.microblog.entity.Post

data class UserDto(
    var id: Long?=null,
    var username: String,
    var email: String,
    var profession: String,
    var ville: String,
    var pays: String,
    var fullname: String,
    var posts: MutableList<Post> = mutableListOf(),
)

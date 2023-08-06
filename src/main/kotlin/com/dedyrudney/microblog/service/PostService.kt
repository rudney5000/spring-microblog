package com.dedyrudney.microblog.service

import com.dedyrudney.microblog.entity.Post
import com.dedyrudney.microblog.repository.PostRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Optional

interface PostService {

    fun getAllPosts(): List<Post>

    fun getPostByUserId(userId:Long):List<Post>

    fun getOnePost(id: Long): Optional<Post>

    fun savePost(post: Post): Post

    fun updatePost(id: Long ,postInput: Post): Optional<Post>

    fun deletePost(id:Long)

}
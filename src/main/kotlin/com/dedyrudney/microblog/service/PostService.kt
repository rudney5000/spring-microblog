package com.dedyrudney.microblog.service

import com.dedyrudney.microblog.entity.Post
import com.dedyrudney.microblog.repository.PostRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PostService @Autowired private constructor(val postRepository: PostRepository){
    fun getPosts(): List<Post> = postRepository.findAll()

    fun getOne(id: Long): Post = postRepository
        .findById(id)
        .orElseThrow{
            IllegalArgumentException("Post with this $id was not found")
        }

    fun savePost(post: Post): Post = postRepository.save(post)

    fun updatePost(id: Long, postInput: Post): Post {
        val postFound = getOne(id)

        postFound.content = postInput.content
        postFound.userId = postInput.userId
        return postRepository.save(postFound)
    }

    fun deletePost(id: Long): String{
        val postFound = getOne(id)

        postRepository.delete(postFound)
        return "Post deleted"
    }
}
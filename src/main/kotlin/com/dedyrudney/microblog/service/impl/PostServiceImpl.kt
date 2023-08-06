package com.dedyrudney.microblog.service.impl

import com.dedyrudney.microblog.entity.Post
import com.dedyrudney.microblog.repository.PostRepository
import com.dedyrudney.microblog.service.PostService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class PostServiceImpl @Autowired constructor(private var postRepository: PostRepository): PostService{

    override fun getAllPosts(): List<Post> = postRepository.findAll()

    override fun getPostByUserId(userId: Long): List<Post> = postRepository.findAllByUserId(userId)

    override fun getOnePost(id: Long): Optional<Post> = postRepository.findById(id)

    override fun savePost(post: Post): Post = postRepository.save(post)

    override fun updatePost(id: Long, postInput: Post): Optional<Post> {
        val optional = getOnePost(id)
        if (optional.isPresent) Optional.empty<Post>()

        return optional.map {
            val postToUpdate = it.copy(
                content = postInput.content
            )
            postRepository.save(postToUpdate)
        }
    }

    override fun deletePost(id: Long) {
        postRepository.findById(id).map {
            postRepository.delete(it)
        }.orElseThrow {
            throw RuntimeException("Id $id not found")
        }
    }
}
package com.dedyrudney.microblog.controller

import com.dedyrudney.microblog.dto.PostDto
import com.dedyrudney.microblog.entity.Post
import com.dedyrudney.microblog.service.PostService
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

@RequestMapping("/api/posts")
@RestController
class PostController private constructor(private val postService: PostService){
    @GetMapping
    fun getAllPost(): List<Post> = postService.getAllPosts()

    @GetMapping("/{id}")
    fun getOnePost(@PathVariable id: Long): ResponseEntity<Post>{
        return postService.getOnePost(id).map {
            ResponseEntity.ok(it)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createPost(@RequestBody post: Post): PostDto {
        val createPost = postService.savePost(post)
        return PostDto(
            createPost.id,
            createPost.content,
            createPost.userId,
        )
    }

    @PutMapping("/{id}")
    fun updatePost(@RequestBody post: Post, @PathVariable id: Long): ResponseEntity<Post> {
        return postService.updatePost(id, post).map {
            ResponseEntity.ok(it)
        }.orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping("/{id}")
    fun deletePost(@PathVariable id: Long): ResponseEntity<Void> {
        postService.deletePost(id)
        return ResponseEntity<Void>(HttpStatus.OK)
    }
}
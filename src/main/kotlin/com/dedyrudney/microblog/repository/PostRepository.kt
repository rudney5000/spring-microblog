package com.dedyrudney.microblog.repository

import com.dedyrudney.microblog.entity.Post
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository: JpaRepository<Post, Long> {
    fun findAllByUserId(userId: Long):List<Post>
}
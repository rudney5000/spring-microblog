package com.dedyrudney.microblog.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.sql.Timestamp
import javax.persistence.*

@Entity
@Table(name = "publication")
data class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long?=null,
    var content: String,
    @Column(name="user_id")
    var userId: Long,
//    @ManyToOne
//    var post: User,
    @CreationTimestamp
    var createdAt: Timestamp?=null,
    @UpdateTimestamp
    var updatedAt: Timestamp? = null,
)
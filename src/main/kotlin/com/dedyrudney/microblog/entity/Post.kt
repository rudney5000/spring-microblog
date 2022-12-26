package com.dedyrudney.microblog.entity

import com.dedyrudney.microblog.entity.User
import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.sql.Timestamp
import javax.persistence.*
import javax.validation.constraints.NotBlank


@Entity
@Table(name = "publication")
data class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long?=null,
    @field:NotBlank(message = "Le contenu de la publication ne peut pas etre vide")
    var content: String,
    @Column(name="user_id")
    var userId: Long,
    @ManyToOne
    @JoinColumn(insertable = false, updatable = false)
    @JsonIgnore
    val user : User?=null,
    @CreationTimestamp
    var createdAt: Timestamp?=null,
    @UpdateTimestamp
    var updatedAt: Timestamp? = null,
)
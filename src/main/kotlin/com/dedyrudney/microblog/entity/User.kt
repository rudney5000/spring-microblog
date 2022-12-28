package com.dedyrudney.microblog.entity

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.sql.Timestamp
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.Size
import kotlin.jvm.Transient

/**
 * L'entite utilisateur (user)
 */
@Entity
@Table(name = "utilisateur")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long?=null,
    @field:Size(min = 3, max = 10, message = "Invalid first name!(3-10 characters)")
    var username: String,
    @Email
    @Column(unique = true)
    var email: String,
    var profession: String,
    var ville: String,
    var pays: String,
    var fullname: String,
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @field:Size(min = 5, max = 10, message = "Invalid password! (5-10 characters)")
    var password: String,
    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "user")
    var posts: MutableList<Post> = mutableListOf(),
    @CreationTimestamp
    var createdAt: Timestamp?=null,
    @UpdateTimestamp
    var updatedAt: Timestamp? = null,
)
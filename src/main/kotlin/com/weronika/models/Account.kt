package com.weronika.models
import kotlinx.serialization.Serializable

val accounts = mutableListOf<Account>()

@Serializable
data class Account(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
)
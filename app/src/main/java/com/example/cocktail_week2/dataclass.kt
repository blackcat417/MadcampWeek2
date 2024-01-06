package com.example.cocktail_week2

import java.io.Serializable
import java.sql.Date

data class RegisterModel(
    var id: String,
    var pw: String
)

data class RegisterResult(
    var message: Boolean
)

data class LoginModel(
    var id: String,
    var pw: String
)

data class LoginResult(
    var UID: Int
)

data class User(
    val UID: Int,
    val id: String,
    val password: String
): Serializable

data class Post(
    val id: Int,
    val content: String,
    val imageUrl: String,
    val createdAt: Date
)

// ImageLog.kt
data class ImageLog(
    val id: Int,
    val imagePath: String,
    val description: String,
    val timestamp: Long
)

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
data class LogEntry(
    val year: String,
    val month: String,
    val day: String,
    val drinkName: String,
    val drinkDescription: String,
    val imageResourceId: Int  // 이미지 리소스 ID. 실제 앱에서는 URL이나 URI가 될 수 있습니다.
)
// 칵테일 데이터 클래스
data class Cocktail(
    val strDrink: String,
    val strCategory: String,
    val strIngredient1: String,
    val strDrinkThumb: String
)
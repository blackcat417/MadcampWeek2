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
    val drinkName: String,
    val imageUrl: String,
    val ingredient: String,
    val instruction: String,
    val rating: Float,
    val timestamp: String
)

// 칵테일 데이터 클래스
data class Cocktail(
    val strDrink: String,
    val strCategory: String,
    val strIngredient1: String,
    val strDrinkThumb: String
)

data class RecCocktails(
    val strDrink: String,
    val strDrinkThumb: String,
    val strIngredient1: String,
    val strInstructions: String
)

data class RecommendModel(
    val base: String,
    val beverage: String,
    val other: String,
    val challenge: Boolean
)

data class LogCocktails(
    val logName: String,
    val logImg: String,
    val logIngredient: String,
    val logRecipe: String,
    val logRating: Float,
    val logTime: String
)

data class MyCocktails(
    val strDrink: String,
    val strDrinkThumb: String,
    val strIngredients: String,
    val strInstructions: String
)

data class AddMy(
    val strDrink: String,
    val strDrinkThumb: String,
    val strInstructions: String,
    val strIngredients: String
)
package com.example.cocktail_week2

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    // 칵테일 목록을 가져오는 메서드
    @GET("cocktails")
    fun getCocktails(
        @Query("base") base: String,
        @Query("beverage") beverage: String,
        @Query("other") other: String
    ): Call<List<Cocktail>>
}

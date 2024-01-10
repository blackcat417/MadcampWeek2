package com.example.cocktail_week2

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
     //칵테일 목록을 가져오는 메서드
//    @GET("cocktails")
//    fun getCocktails(
//        @Query("base") base: String,
//        @Query("beverage") beverage: String,
//        @Query("other") other: String
//    ): Call<List<Cocktail>>

    @POST("recommend")
    fun getRecommends(@Body request: RecommendModel): Call<List<RecCocktails>>

    @POST("moveToLog")
    fun addLogs(@Body request: LogEntry): Call<ResponseBody>

    @GET("logs")
    fun logCocktails(): Call<List<LogCocktails>>

    @GET("mycocktails")
    fun MyCocktails(): Call<List<MyCocktails>>

    @POST("addMyCocktails")
    fun addMyCocktails(@Body request: AddMy): Call<ResponseBody>
}

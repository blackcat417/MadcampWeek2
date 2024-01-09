package com.example.cocktail_week2.Log

import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktail_week2.ApiService
import com.example.cocktail_week2.Log.LogAdapter
import com.example.cocktail_week2.LogEntry
import com.example.cocktail_week2.MainActivity
import com.example.cocktail_week2.R
import com.example.cocktail_week2.LogCocktails
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LogActivity : AppCompatActivity() {
    private lateinit var logAdapter: LogAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var fabGoToMain: FloatingActionButton

    val retrofit = Retrofit.Builder()
        .baseUrl("http://192.249.30.207:3800")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create(ApiService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_cocktail)
        supportActionBar?.hide()

        recyclerView = findViewById(R.id.recyclerView)
        searchView = findViewById(R.id.searchView)
        fabGoToMain = findViewById(R.id.fabGoToMain)

        recyclerView.layoutManager = LinearLayoutManager(this)
        logAdapter = LogAdapter(listOf())
        recyclerView.adapter = logAdapter

        fabGoToMain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        loadLogs()
    }

    private fun loadLogs() {
        apiService.logCocktails().enqueue(object : Callback<List<LogCocktails>> {
            override fun onResponse(
                call: Call<List<LogCocktails>>,
                response: Response<List<LogCocktails>>
            ) {
                if (response.isSuccessful) {
                    val apiResponse = response.body() ?: return
                    val logCocktails = apiResponse.map {
                        LogCocktails(
                            logName = it.logName,
                            logIngredient = it.logIngredient,
                            logRecipe = it.logRecipe,
                            logImg = it.logImg // URL 사용
                        )
                    } ?: emptyList()
                    logAdapter.updateList(logCocktails)
                } else {
                    // Handle error
                }
            }

            override fun onFailure(call: Call<List<LogCocktails>>, t: Throwable) {
                // Handle error
            }
        })
    }

}

//        val imageViewCocktail: ImageView = findViewById(R.id.logoImageView)
//        Glide.with(this)
//            .load(imageUrl)
//            .into(imageViewCocktail)
//        // 로그 목록에 새 로그 추가
//
//        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
//        recyclerView.layoutManager = LinearLayoutManager(this)  // 2개의 컬럼으로 표시
//
//        val imageLogs = mutableListOf<LogEntry>()
//        // 로그 목록 불러오기
//        loadImageLogs(object : LogLoadListener {
//            override fun onLogsLoaded(logs: List<LogEntry>) {
//                imageLogs.addAll(logs)
//                // 새로운 항목 추가
//                val newLog = LogEntry(drinkName, imageUrl, ingredient, instruction)
//                imageLogs.add(newLog)
//
//                // RecyclerView 업데이트
//                updateRecyclerView(imageLogs)
//            }
//        })
//    }
//
//    // 이미지 로그 데이터를 로드하는 함수
//    private fun loadImageLogs(listener: LogLoadListener) {
//        apiService.getCocktailLogs().enqueue(object : Callback<List<LogEntry>> {
//            override fun onResponse(
//                call: Call<List<LogEntry>>,
//                response: Response<List<LogEntry>>
//            ) {
//                if (response.isSuccessful) {
//                    val logs = response.body() ?: emptyList()
//                    listener.onLogsLoaded(logs)
//                } else {
//                    Log.e("LogActivity", "Error: ${response.errorBody()?.string()}")
//                }
//            }
//
//            override fun onFailure(call: Call<List<LogEntry>>, t: Throwable) {
//                Log.e("LogActivity", "Network error: ${t.message}")
//            }
//        })
//    }
//    interface LogLoadListener {
//        fun onLogsLoaded(logs: List<LogEntry>)
//    }
//
//}
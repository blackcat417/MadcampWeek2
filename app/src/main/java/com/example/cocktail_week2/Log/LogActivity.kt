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

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { filterLogs(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { filterLogs(it) }
                return true
            }
        })
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
                            logImg = it.logImg, // URL 사용
                            logRating = it.logRating,
                            logTime = it.logTime
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

    private var allLogs: List<LogCocktails> = listOf()

    private fun filterLogs(query: String) {
        val filteredLogs = allLogs.filter {
            it.logName.contains(query, ignoreCase = true) ||
                    it.logIngredient.contains(query, ignoreCase = true)
        }
        logAdapter.updateList(filteredLogs)
    }
}
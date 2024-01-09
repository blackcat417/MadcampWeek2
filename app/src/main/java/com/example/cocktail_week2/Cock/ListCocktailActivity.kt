package com.example.cocktail_week2.Cock

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktail_week2.Cocktail
import com.example.cocktail_week2.MainActivity
import com.example.cocktail_week2.R
import com.example.cocktail_week2.RetroInterface
import com.example.cocktail_week2.databinding.ActivityListCocktailBinding
import com.example.cocktail_week2.databinding.ActivityLoginBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListCocktailActivity : AppCompatActivity() {

    private lateinit var cocktailAdapter: CocktailAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var fabGoToMain: FloatingActionButton
    private lateinit var apiService: RetroInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_cocktail)
        supportActionBar?.hide()

        apiService = RetroInterface.create()

        recyclerView = findViewById(R.id.recyclerView)
        searchView = findViewById(R.id.searchView)
        fabGoToMain = findViewById(R.id.fabGoToMain)

        recyclerView.layoutManager = LinearLayoutManager(this)
        cocktailAdapter = CocktailAdapter(listOf())
        recyclerView.adapter = cocktailAdapter

        fabGoToMain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        loadCocktails()
        setupSearchView()
    }

    private fun loadCocktails() {
        apiService.getCocktails().enqueue(object : Callback<List<Cocktail>> {
            override fun onResponse(call: Call<List<Cocktail>>, response: Response<List<Cocktail>>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body() ?: return
                    val cocktails = apiResponse.map {
                        Cocktail(
                            strDrink = it.strDrink,
                            strCategory = it.strCategory,
                            strIngredient1 = it.strIngredient1,
                            strDrinkThumb = it.strDrinkThumb // URL 사용
                        )
                    } ?: emptyList()
                    cocktailAdapter.updateList(cocktails)
                } else {
                    // Handle error
                }
            }

            override fun onFailure(call: Call<List<Cocktail>>, t: Throwable) {
                // Handle error
            }
        })
    }

    private fun setupSearchView() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { filterCocktails(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { filterCocktails(it) }
                return true
            }
        })
    }

    private fun filterCocktails(query: String) {
        // Implement filtering logic
    }
}
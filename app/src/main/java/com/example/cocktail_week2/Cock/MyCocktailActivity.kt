package com.example.cocktail_week2.Cock

import android.accounts.AuthenticatorDescription
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktail_week2.AddMy
import com.example.cocktail_week2.ApiService
import com.example.cocktail_week2.Cocktail
import com.example.cocktail_week2.LogCocktails
import com.example.cocktail_week2.LogEntry
import com.example.cocktail_week2.MainActivity
import com.example.cocktail_week2.MyCocktails
import com.example.cocktail_week2.R
import com.example.cocktail_week2.RetroInterface
import com.google.android.material.floatingactionbutton.FloatingActionButton
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyCocktailActivity : AppCompatActivity() {

    private lateinit var mycocktailAdapter:MyCocktailAdapter
    private lateinit var fabGoToMain: FloatingActionButton
    private lateinit var fabAddLog: FloatingActionButton
    private lateinit var recyclerView: RecyclerView

    val retrofit = Retrofit.Builder()
        .baseUrl("http://192.249.30.207:3800")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create(ApiService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log)
        supportActionBar?.hide()

        recyclerView = findViewById(R.id.recyclerView)
        fabAddLog = findViewById(R.id.addLog)
        fabGoToMain = findViewById(R.id.fabGoToMain)

        recyclerView.layoutManager = GridLayoutManager(this,2)
        mycocktailAdapter = MyCocktailAdapter(listOf())
        recyclerView.adapter = mycocktailAdapter

        fabGoToMain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val fabAddLog = findViewById<FloatingActionButton>(R.id.addLog)
        fabAddLog.setOnClickListener {
            // 여기에서 새 로그 추가를 위한 다이얼로그 또는 액티비티를 띄웁니다.
            // 예: 칵테일 이름, 재료, 조리 방법 등을 입력할 수 있는 다이얼로그
            showAddLogDialog()
        }
        loadMyCocktails()
    }

    private fun loadMyCocktails(){
        apiService.MyCocktails().enqueue(object : Callback<List<MyCocktails>> {
            override fun onResponse(call: Call<List<MyCocktails>>, response: Response<List<MyCocktails>>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body() ?: return
                    val addMyCocktails = apiResponse.map {
                        MyCocktails(
                            strDrink = it.strDrink,
                            strDrinkThumb = it.strDrinkThumb,
                            strIngredients = it.strIngredients,
                            strInstructions = it.strInstructions
                        )
                    } ?: emptyList()
                    mycocktailAdapter.updateList(addMyCocktails)
                } else {
                    // Handle error
                }
            }
            override fun onFailure(call: Call<List<MyCocktails>>, t: Throwable) {
                // Handle error
            }
        })
    }


    private fun showAddLogDialog() {
        // 다이얼로그 구현: 사용자 입력을 받아서 새 로그를 추가하는 로직
        val layoutInflater = LayoutInflater.from(this)
        val view = layoutInflater.inflate(R.layout.dialog_add_cocktail, null)

        val editTextName = view.findViewById<EditText>(R.id.editTextCocktailName)
        val editTextUrl = view.findViewById<EditText>(R.id.editTextImageUrl)
        val editTextDescription = view.findViewById<EditText>(R.id.editTextDescription)
        val editTextIngredients = view.findViewById<EditText>(R.id.editTextIngredients)

        AlertDialog.Builder(this)
            .setView(view)
            .setPositiveButton("추가") { dialog, _ ->
                val strDrink = editTextName.text.toString()
                val strDrinkThumb = editTextUrl.text.toString()
                val strInstructions = editTextDescription.text.toString()
                val strIngredients = editTextIngredients.text.toString()

                // 여기에서 DB에 데이터를 저장하는 로직을 구현합니다.
                saveCocktailToDb(strDrink, strDrinkThumb, strInstructions, strIngredients)
                loadMyCocktails()
            }
            .setNegativeButton("취소", null)
            .show()
    }

    private fun saveCocktailToDb(strDrink: String, strDrinkThumb: String, strInstructions: String, strIngredients: String){
        val newCocktail = AddMy(strDrink, strDrinkThumb, strInstructions, strIngredients)
        apiService.addMyCocktails(newCocktail).enqueue(object : Callback<ResponseBody> {

            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                if (response.isSuccessful) {
                        Toast.makeText(applicationContext, "칵테일이 추가되었습니다 !", Toast.LENGTH_SHORT).show()
                        Log.d("MyCocktailActivity", "Response received: ${response.body()}")
                } else {
                    Toast.makeText(applicationContext, "오류 발생: ${response.errorBody()?.string()}", Toast.LENGTH_SHORT).show()
                    Log.d("MyCocktailActivity", "Error response: ${response.errorBody()?.string()}")
                }
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                // 네트워크 오류 처리
                Toast.makeText(applicationContext, "네트워크 오류: ${t.message}", Toast.LENGTH_SHORT).show()
                Log.e("MyCocktailActivity", "API call failed", t)
            }
        })

    }

    // 기타 필요한 메소드 구현
}

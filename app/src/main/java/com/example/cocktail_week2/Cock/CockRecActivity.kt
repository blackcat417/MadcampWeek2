package com.example.cocktail_week2.Cock

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.cocktail_week2.Cocktail
import com.example.cocktail_week2.MainActivity
import com.example.cocktail_week2.R
import com.example.cocktail_week2.ApiService
import com.example.cocktail_week2.LogEntry
import com.example.cocktail_week2.LoginModel
import com.example.cocktail_week2.RecCocktails
import com.example.cocktail_week2.RecommendModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CockRecActivity : AppCompatActivity() {
    private lateinit var apiService: ApiService
    private fun showRecommendationDialog(drinkName: String, imageUrl:String, ingredient: String, instruction:String) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_cocktail, null)
        val imageView = dialogView.findViewById<ImageView>(R.id.imageViewCocktail)
        val nameView = dialogView.findViewById<TextView>(R.id.textViewCocktailName)
        val ingredientView = dialogView.findViewById<TextView>(R.id.textViewCocktailIngredient)
        val instructionView = dialogView.findViewById<TextView>(R.id.textViewCocktailInstruction)

        Glide.with(this)
            .load(imageUrl)
            .into(imageView)

        nameView.text = drinkName
        ingredientView.text = ingredient
        instructionView.text = instruction

        AlertDialog.Builder(this).apply {
            setView(dialogView)
            setPositiveButton("확인", null)
            setNeutralButton("MyLog에 추가하기") { _,_ ->

                val newLog = LogEntry(drinkName, imageUrl, ingredient, instruction)
                apiService.addLogs(newLog).enqueue(object : retrofit2.Callback<ResponseBody> {

                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {
                        if (response.isSuccessful) {
                            val responseString = response.body()?.string() ?:""
                            // 성공적인 서버 응답 처리
                            Toast.makeText(applicationContext, "로그에 추가되었습니다: $responseString", Toast.LENGTH_SHORT).show()
                        } else {
                            // 서버에서 실패한 응답 처리
                            Toast.makeText(applicationContext, "오류 발생: ${response.errorBody()?.string()}", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        // 네트워크 오류 처리
                        Toast.makeText(applicationContext, "네트워크 오류: ${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })

            }
        }.show()
    }

    private fun getCocktailRecommendation(
        base: String,
        beverage: String,
        other: String,
        challenge: Boolean,
        onResult: (String?) -> Unit
    ) {

        val safeBase = if (base.isBlank()) "%" else base
        val safeBeverage = if (beverage.isBlank()) "%" else beverage
        val safeOther = if (other.isBlank()) "%" else other

        val SelectedRec = RecommendModel(safeBase, safeBeverage, safeOther, challenge)

        val call = apiService.getRecommends(SelectedRec)
        call.enqueue(object : retrofit2.Callback<List<RecCocktails>> {
            override fun onResponse(
                call: Call<List<RecCocktails>>,
                response: Response<List<RecCocktails>>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    val firstCocktail = responseBody?.firstOrNull()
                    val drinkName = firstCocktail?.strDrink ?: "Unknown"
                    val imageUrl = firstCocktail?.strDrinkThumb ?: "Unknown"
                    val ingredient = firstCocktail?.strIngredient1 ?: "Unknown"
                    val instruction = firstCocktail?.strInstructions ?: "Unknown"

                    if(responseBody.isNullOrEmpty()){
                        Log.d("CocktailRecommendation","Response body is empty or null")
                        onResult(null)
                    }
                    else{
                        showRecommendationDialog(drinkName, imageUrl, ingredient, instruction)
                    }

                } else {
                    Log.d("CocktailRecommendation","Response is not successful: ${response.errorBody()?.string()}")
                    onResult(null)
                }
            }

            override fun onFailure(call: Call<List<RecCocktails>>, t: Throwable) {
                Log.d("CocktailRecommendation","Request failed: ${t.message}")
                onResult(null)
            }
        })
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cock_rec)
        supportActionBar?.hide()

        apiService = Retrofit.Builder()
            .baseUrl("http://192.249.30.207:3800") // 실제 서버 URL로 변경하세요.
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

        val spinnerBase: Spinner = findViewById(R.id.spinnerBase)
        val spinnerBeverage: Spinner = findViewById(R.id.spinnerBeverage)
        val spinnerOther: Spinner = findViewById(R.id.spinnerOther)
        val switchChallenge: Switch = findViewById(R.id.switchChallenge)
        val buttonRecommend: Button = findViewById(R.id.buttonRecommend)

        // 스피너에 데이터 설정 (예시)
        // 실제 앱에서는 데이터베이스 또는 API에서 가져오거나 정적 배열을 사용할 수 있습니다.
        // 스피너 데이터 설정 (실제 앱에서는 서버 또는 로컬 데이터를 사용하세요)
        val bases =
            arrayOf("", "Vodka", "Gin", "Rum", "Whisky", "Wine", "Tequila", "Kalua", "Brandy")
        val beverages = arrayOf("", "Juice", "Soft Drink", "Milk", "Coffee", "Water")
        val others = arrayOf("", "Egg", "Lime", "Lemon", "Cream", "Sugar", "Salt", "Olive")

        spinnerBase.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, bases)
        spinnerBeverage.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, beverages)
        spinnerOther.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, others)

        buttonRecommend.setOnClickListener {
            // 사용자 선택 정보 가져오기
            val base = spinnerBase.selectedItem.toString()
            val beverage = spinnerBeverage.selectedItem.toString()
            val other = spinnerOther.selectedItem.toString()
            val challenge = switchChallenge.isChecked

            // 서버에서 칵테일 추천 받기
            getCocktailRecommendation(base, beverage, other, challenge) { recommendedCocktail ->
                if (recommendedCocktail == null) {
                    Toast.makeText(
                        this,
                        "${base}, ${beverage}, ${other},추천 칵테일을 찾을 수 없습니다.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
            // FloatingActionButton 설정
            findViewById<FloatingActionButton>(R.id.fabGoToMain).setOnClickListener {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }
}
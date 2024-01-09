package com.example.cocktail_week2.Cock

import android.accounts.AuthenticatorDescription
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.cocktail_week2.MainActivity
import com.example.cocktail_week2.R
import com.example.cocktail_week2.RetroInterface
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MyCocktailActivity : AppCompatActivity() {
    private lateinit var fabGoToMain: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log)
        supportActionBar?.hide()

        fabGoToMain = findViewById(R.id.fabGoToMain)

        val fabAddLog = findViewById<FloatingActionButton>(R.id.addLog)
        fabAddLog.setOnClickListener {
            // 여기에서 새 로그 추가를 위한 다이얼로그 또는 액티비티를 띄웁니다.
            // 예: 칵테일 이름, 재료, 조리 방법 등을 입력할 수 있는 다이얼로그
            showAddLogDialog()
        }

        fabGoToMain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
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
                val name = editTextName.text.toString()
                val url = editTextUrl.text.toString()
                val description = editTextDescription.text.toString()
                val ingredients = editTextIngredients.text.toString()

                // 여기에서 DB에 데이터를 저장하는 로직을 구현합니다.
                saveCocktailToDb(name, url, description, ingredients)
            }
            .setNegativeButton("취소", null)
            .show()
    }

    private fun saveCocktailToDb(name: String, url: String, description: String, ingredients: String){

    }

    // 기타 필요한 메소드 구현
}

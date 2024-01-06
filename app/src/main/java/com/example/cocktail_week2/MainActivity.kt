package com.example.cocktail_week2

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 첫 번째 이미지 버튼
        findViewById<ImageButton>(R.id.imageButton1).setOnClickListener {
            val intent = Intent(this, LogActivity::class.java)
            startActivity(intent)
        }

        // 두 번째 이미지 버튼
        findViewById<ImageButton>(R.id.imageButton2).setOnClickListener {
            val intent = Intent(this, CockRecActivity::class.java)
            startActivity(intent)
        }

        // 세 번째 이미지 버튼
        findViewById<ImageButton>(R.id.imageButton3).setOnClickListener {
            val intent = Intent(this, MyCocktailActivity::class.java)
            startActivity(intent)
        }

        // 네 번째 이미지 버튼
        findViewById<ImageButton>(R.id.imageButton4).setOnClickListener {
            val intent = Intent(this, ListCocktailActivity::class.java)
            startActivity(intent)
        }
    }
}

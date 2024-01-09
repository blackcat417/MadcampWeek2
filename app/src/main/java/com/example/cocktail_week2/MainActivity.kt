package com.example.cocktail_week2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.cocktail_week2.Cock.CockRecActivity
import com.example.cocktail_week2.Cock.ListCocktailActivity
import com.example.cocktail_week2.Cock.MyCocktailActivity
import com.example.cocktail_week2.Log.LogActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        // 첫 번째 이미지 버튼
        findViewById<Button>(R.id.Button1).setOnClickListener {
            val intent = Intent(this, LogActivity::class.java)
            startActivity(intent)
        }

        // 두 번째 이미지 버튼
        findViewById<Button>(R.id.Button2).setOnClickListener {
            val intent = Intent(this, CockRecActivity::class.java)
            startActivity(intent)
        }

        // 세 번째 이미지 버튼
        findViewById<Button>(R.id.Button3).setOnClickListener {
            val intent = Intent(this, MyCocktailActivity::class.java)
            startActivity(intent)
        }

        // 네 번째 이미지 버튼
        findViewById<Button>(R.id.Button4).setOnClickListener {
            val intent = Intent(this, ListCocktailActivity::class.java)
            startActivity(intent)
        }

        findViewById<ImageView>(R.id.imageView2).setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }
}
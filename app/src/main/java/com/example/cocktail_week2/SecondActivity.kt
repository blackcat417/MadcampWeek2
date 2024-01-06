package com.example.cocktail_week2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cocktail_week2.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        val id = intent.getStringExtra("id")
        binding.textView.text = "$id 님 안녕하세요."

    }
}
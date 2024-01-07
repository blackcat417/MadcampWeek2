package com.example.cocktail_week2.Login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Toast
import com.example.cocktail_week2.MainActivity
import com.example.cocktail_week2.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            binding.apply {
                val id = inputID.text.toString()
                val pw = inputPw.text.toString()

                if (id == "" || pw == "") {
                    Toast.makeText(applicationContext, "입력하지 않은 정보가 있습니다.", Toast.LENGTH_SHORT)
                        .show()
                    return@setOnClickListener
                }
                // 로그인 로직을 구현합니다.
                Toast.makeText(applicationContext, "로그인 성공", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                intent.putExtra("id", binding.inputID.text.toString())
                startActivity(intent)
            }
        }

        binding.registerButton.setOnClickListener {
            // 회원가입 화면으로 전환합니다.
            val registerIntent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(registerIntent)
        }
    }
}
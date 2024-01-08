package com.example.cocktail_week2.Login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.cocktail_week2.LoginModel
import com.example.cocktail_week2.LoginResult
import com.example.cocktail_week2.MainActivity
import com.example.cocktail_week2.RegisterModel
import com.example.cocktail_week2.RegisterResult
import com.example.cocktail_week2.RetroInterface
import com.example.cocktail_week2.User
import com.example.cocktail_week2.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    val api = RetroInterface.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerButton.setOnClickListener {
            binding.apply {
                val id = inputID.text.toString()
                val pw = inputPw.text.toString()

                if (id == "" || pw == "") {
                    Toast.makeText(applicationContext, "입력하지 않은 정보가 있습니다.", Toast.LENGTH_SHORT)
                        .show()
                    return@setOnClickListener
                }
            }
                val newUser =
                    RegisterModel(binding.inputID.text.toString(), binding.inputPw.text.toString())
                api.register(newUser).enqueue(object : retrofit2.Callback<RegisterResult> {
                    override fun onResponse(
                        call: Call<RegisterResult>,
                        response: Response<RegisterResult>
                    ) {
                        val result = response.body()?.message ?: return
                        if (result)
                            Toast.makeText(applicationContext, "회원가입 성공", Toast.LENGTH_SHORT).show()
                        else
                            Toast.makeText(
                                applicationContext,
                                "회원가입 실패, 이미 존재하는 아이디 입니다.",
                                Toast.LENGTH_SHORT
                            ).show()
                    }
                    override fun onFailure(call: Call<RegisterResult>, t: Throwable) {
                        Log.d("test0", t.message.toString())
                    }
                })
            }
            binding.loginButton.setOnClickListener {
                binding.apply {
                    val id = inputID.text.toString()
                    val pw = inputPw.text.toString()

                    if (id == "" || pw == "") {
                        Toast.makeText(applicationContext, "입력하지 않은 정보가 있습니다.", Toast.LENGTH_SHORT)
                            .show()
                        return@setOnClickListener
                    }
                }
                val loginUser = LoginModel(binding.inputID.text.toString(), binding.inputPw.text.toString())
                api.login(loginUser).enqueue(object: Callback<LoginResult>{
                    override fun onResponse(call: Call<LoginResult>, response: Response<LoginResult>) {
                        val user_uid = response.body()?.UID ?: return
                        if(user_uid != -1) {
                            Toast.makeText(applicationContext, "로그인 성공", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            intent.putExtra("id", binding.inputID.text.toString())
                            startActivity(intent)

                            Log.d("test1", user_uid.toString())
                        }
                        else{
                            Toast.makeText(applicationContext, "로그인 실패, 아이디 또는 비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show()
                        }
                    }
                    override fun onFailure(call: Call<LoginResult>, t: Throwable) {
                        Log.d("test2", t.message.toString())
                    }
                })
            }
    }
}
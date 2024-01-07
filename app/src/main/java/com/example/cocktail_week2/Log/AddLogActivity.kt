package com.example.cocktail_week2.Log

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cocktail_week2.R

class AddLogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_log)
        supportActionBar?.hide()

        // 스피너와 EditText 찾기
        val spinnerYear: Spinner = findViewById(R.id.spinnerYear)
        val spinnerMonth: Spinner = findViewById(R.id.spinnerMonth)
        val spinnerDay: Spinner = findViewById(R.id.spinnerDay)
        val editTextDrinkName: EditText = findViewById(R.id.editTextDrinkName)
        val editTextDrinkDescription: EditText = findViewById(R.id.editTextDrinkDescription)
        val buttonAddLog: Button = findViewById(R.id.buttonAddLog)

        // 연도, 월, 일 데이터 설정하기 (실제 앱에서는 동적으로 설정)
        val years = arrayOf("2020", "2021", "2022", "2023", "2024")
        val months = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12")
        val days = (1..31).map { it.toString() }.toTypedArray()
        // 스피너 어댑터 설정
        spinnerYear.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, years)
        spinnerMonth.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, months)
        spinnerDay.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, days)

        // 버튼 클릭 리스너 설정
        buttonAddLog.setOnClickListener {
            // 스피너와 EditText에서 정보 가져오기
            val selectedYear = spinnerYear.selectedItem.toString()
            val selectedMonth = spinnerMonth.selectedItem.toString()
            val selectedDay = spinnerDay.selectedItem.toString()
            val drinkName = editTextDrinkName.text.toString()
            val drinkDescription = editTextDrinkDescription.text.toString()

            // 로그에 정보 추가 (여기서는 Toast 메시지로 표시)
            Toast.makeText(this, "Log에 추가되었습니다!", Toast.LENGTH_LONG).show()

            // 로그 데이터를 LogActivity로 전달
            val intent = Intent(this, LogActivity::class.java).apply {
                putExtra("year", selectedYear)
                putExtra("month", selectedMonth)
                putExtra("day", selectedDay)
                putExtra("drinkName", drinkName)
                putExtra("drinkDescription", drinkDescription)
                putExtra("imageResourceId", R.drawable.cocktail1)  // 예시 이미지 리소스 ID
            }
            startActivity(intent)
            // 현재 액티비티 종료하여 뒤로 가기 스택에서 제거
            finish()
        }
    }
}

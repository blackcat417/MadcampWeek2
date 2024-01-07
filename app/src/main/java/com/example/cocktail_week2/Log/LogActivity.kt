package com.example.cocktail_week2.Log

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktail_week2.ImageLog
import com.example.cocktail_week2.LogEntry
import com.example.cocktail_week2.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class LogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log)
        supportActionBar?.hide()

        // FloatingActionButton 찾기
        val fabAddLog = findViewById<FloatingActionButton>(R.id.fabAddLog)

        // FloatingActionButton에 클릭 리스너 설정
        fabAddLog.setOnClickListener {
            // 여기에 log 추가 액티비티를 시작하는 코드 또는 다이얼로그를 표시하는 코드를 추가합니다.

            // 예: AddLogActivity 시작
            val intent = Intent(this, AddLogActivity::class.java)
            startActivity(intent)

            // 또는, 커스텀 다이얼로그나 프래그먼트를 사용하여 log 추가 인터페이스를 표시할 수 있습니다.
        }

        // LogActivity 내 onCreate 메서드 안
        val year = intent.getStringExtra("year") ?: "Unknown"
        val month = intent.getStringExtra("month") ?: "Unknown"
        val day = intent.getStringExtra("day") ?: "Unknown"
        val drinkName = intent.getStringExtra("drinkName") ?: "Unknown"
        val drinkDescription = intent.getStringExtra("drinkDescription") ?: "Unknown"
        val imageResourceId = intent.getIntExtra("imageResourceId", 0) // 기본값으로 0 설정

        // 로그 목록에 새 로그 추가
        val newLog = LogEntry(year, month, day, drinkName, drinkDescription, imageResourceId)
        val logs = mutableListOf<LogEntry>()
        logs.add(newLog)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)  // 2개의 컬럼으로 표시

        val imageLogs = mutableListOf<LogEntry>()
        imageLogs.add(LogEntry("2021", "01", "01", "술 이름", "술에 대한 설명", R.drawable.cocktail2)
        )
        recyclerView.adapter = LogAdapter(imageLogs)
    }

    // 이미지 로그 데이터를 로드하는 함수 (여기서는 예시 데이터를 생성)
    private fun loadImageLogs(): List<ImageLog> {
        // 실제 앱에서는 데이터베이스 또는 서버에서 데이터를 로드해야 합니다.
        return listOf(/* 이미지 로그 데이터 */)
    }
}

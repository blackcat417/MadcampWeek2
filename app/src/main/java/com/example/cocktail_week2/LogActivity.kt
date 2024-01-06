package com.example.cocktail_week2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // 이미지 로그 데이터 로드
        val imageLogs = loadImageLogs()

        // 어댑터 설정
        val adapter = LogAdapter(imageLogs)
        recyclerView.adapter = adapter
    }

    // 이미지 로그 데이터를 로드하는 함수 (여기서는 예시 데이터를 생성)
    private fun loadImageLogs(): List<ImageLog> {
        // 실제 앱에서는 데이터베이스 또는 서버에서 데이터를 로드해야 합니다.
        return listOf(/* 이미지 로그 데이터 */)
    }
}

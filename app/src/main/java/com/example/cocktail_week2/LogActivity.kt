package com.example.cocktail_week2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class LogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log)

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

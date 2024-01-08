package com.example.cocktail_week2.MyPage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cocktail_week2.R

class MyPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // my_page.xml 레이아웃을 현재 액티비티의 뷰로 설정합니다.
        setContentView(R.layout.activity_my_page)

        // 액션바를 숨깁니다 (필요에 따라).
        supportActionBar?.hide()

        // 여기서부터 추가적인 초기화 코드를 작성할 수 있습니다.
        // 예를 들어, 프로필 사진, 사용자 ID, 레이더 차트 등의 UI 컴포넌트를 설정하거나
        // 서버에서 사용자 정보를 가져와서 화면에 표시하는 로직을 구현할 수 있습니다.

        // 예: 사용자 ID 설정
        // val userIdTextView: TextView = findViewById(R.id.userIdTextView)
        // userIdTextView.text = "사용자 ID 여기에 설정"
    }
}

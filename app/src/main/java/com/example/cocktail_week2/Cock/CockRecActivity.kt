package com.example.cocktail_week2.Cock

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Switch
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.cocktail_week2.MainActivity
import com.example.cocktail_week2.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CockRecActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cock_rec)
        supportActionBar?.hide()


        val spinnerBase: Spinner = findViewById(R.id.spinnerBase)
        val spinnerBeverage: Spinner = findViewById(R.id.spinnerBeverage)
        val spinnerOther: Spinner = findViewById(R.id.spinnerOther)
        val switchChallenge: Switch = findViewById(R.id.switchChallenge)
        val buttonRecommend: Button = findViewById(R.id.buttonRecommend)

        // 스피너에 데이터 설정 (예시)
        // 실제 앱에서는 데이터베이스 또는 API에서 가져오거나 정적 배열을 사용할 수 있습니다.

        // 베이스 술 예시 데이터
        val bases = arrayOf("보드카", "럼", "진")
        val adapterBase = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, bases)
        spinnerBase.adapter = adapterBase

        // 음료 예시 데이터
        val beverages = arrayOf("토닉 워터", "콜라", "오렌지 주스")
        val adapterBeverage = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, beverages)
        spinnerBeverage.adapter = adapterBeverage

        // 기타 재료 예시 데이터
        val others = arrayOf("라임", "민트 잎", "설탕")
        val adapterOther = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, others)
        spinnerOther.adapter = adapterOther


        buttonRecommend.setOnClickListener {
            // 사용자 선택 정보 가져오기
            val base = spinnerBase.selectedItem.toString()
            val beverage = spinnerBeverage.selectedItem.toString()
            val other = spinnerOther.selectedItem.toString()
            val challenge = switchChallenge.isChecked

            // 추천 로직 구현 (여기서는 단순화된 예시)
            val recommendedCocktail = getCocktailRecommendation(base, beverage, other, challenge)

            // 추천 결과를 팝업(다이얼로그)으로 표시
            showRecommendationDialog(recommendedCocktail)
        }
        // FloatingActionButton 찾기
        val fabGoToMain: FloatingActionButton = findViewById(R.id.fabGoToMain)

        // FloatingActionButton에 클릭 리스너 설정
        fabGoToMain.setOnClickListener {
            // MainActivity로 이동하는 인텐트 생성
            val intent = Intent(this, MainActivity::class.java)

            // 인텐트 시작
            startActivity(intent)

            // 옵션: 현재 액티비티를 종료 (뒤로가기 스택에서 제거)
            finish()
        }
    }

    private fun getCocktailRecommendation(base: String, beverage: String, other: String, challenge: Boolean): String {
        // 추천 로직 구현
        // 여기서는 단순히 선택된 옵션을 문자열로 반환합니다.
        // 실제 앱에서는 복잡한 로직을 구현할 수 있습니다.
        return "추천 칵테일: $base + $beverage + $other (도전: $challenge)"
    }

    private fun showRecommendationDialog(recommendedCocktail: String) {
        AlertDialog.Builder(this).apply {
            setTitle("추천 칵테일")
            setMessage(recommendedCocktail)
            setPositiveButton("확인", null)
        }.show()
    }
}
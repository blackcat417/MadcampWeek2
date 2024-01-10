package com.example.cocktail_week2

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cocktail_week2.Log.LogAdapter
import com.github.mikephil.charting.charts.RadarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProfileActivity : AppCompatActivity() {
    val retrofit = Retrofit.Builder()
        .baseUrl("http://192.249.30.207:3800")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create(ApiService::class.java)

    private fun updateUserInfo(userId: String, preferences: List<Float>) {
        val userInfo = User(userId, preferences[0], preferences[1], preferences[2], preferences[3], preferences[4])

        apiService.editUserInfo(userInfo).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    Toast.makeText(applicationContext, "선호도가 업데이트되었습니다.", Toast.LENGTH_SHORT).show()
                } else {
                    val errorMessage = response.errorBody()?.string() ?: "알 수 없는 오류 발생"
                    Toast.makeText(applicationContext, "업데이트 실패: $errorMessage", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                val message = t.message ?: "알 수 없는 네트워크 오류"
                Toast.makeText(applicationContext, "네트워크 오류: $message", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showTastePreferencesDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_taste_preferences, null)
        val dialog = AlertDialog.Builder(this)
            .setTitle("맛 선호도를 선택해주세요 !")
            .setView(dialogView)
            .create()

        val sliderDry = dialogView.findViewById<SeekBar>(R.id.sliderDry)
        val sliderSour = dialogView.findViewById<SeekBar>(R.id.sliderSour)
        val sliderSweet = dialogView.findViewById<SeekBar>(R.id.sliderSweet)
        val sliderSmooth = dialogView.findViewById<SeekBar>(R.id.sliderSmooth)
        val sliderHot = dialogView.findViewById<SeekBar>(R.id.sliderHot)
        // Initialize and set up other sliders (sour, sweet, smooth, hot)
        // ...

        dialogView.findViewById<Button>(R.id.btnConfirm).setOnClickListener {
            val dryPreference = sliderDry.progress.toFloat()
            val sourPreference = sliderSour.progress.toFloat()
            val sweetPreference = sliderSweet.progress.toFloat()
            val smoothPreference = sliderSmooth.progress.toFloat()
            val hotPreference = sliderHot.progress.toFloat()

            val preferences = listOf(dryPreference, sourPreference, sweetPreference, smoothPreference, hotPreference)
            setupRadarChart(findViewById(R.id.radarChart), preferences)

            updateUserInfo(myClass.userID, preferences)

            dialog.dismiss()
        }

        dialog.show()
    }

    private fun loadUserInfo(userId: String) {
        apiService.getUserPreferences(userId).enqueue(object : Callback<Flavors> {
            override fun onResponse(call: Call<Flavors>, response: Response<Flavors>) {
                if (response.isSuccessful) {
                    val userPreferences = response.body()
                    val preferences = listOf(
                        userPreferences?.flavor1 ?: 0f,
                        userPreferences?.flavor2 ?: 0f,
                        userPreferences?.flavor3 ?: 0f,
                        userPreferences?.flavor4 ?: 0f,
                        userPreferences?.flavor5 ?: 0f
                    )
                    setupRadarChart(findViewById(R.id.radarChart), preferences)
                } else {
                    // Handle error
                }
            }

            override fun onFailure(call: Call<Flavors>, t: Throwable) {
                // Handle error
            }
        })
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        supportActionBar?.hide()

        // ... other initializations ...
        var userIdTextView = findViewById<TextView>(R.id.topBar)
        val editProfileButton = findViewById<Button>(R.id.btnEditProfile)
        val profileImageView = findViewById<ImageView>(R.id.ivProfileImage)
        val cocktailCountTextView = findViewById<TextView>(R.id.tvCocktailCount)
        val radarChart = findViewById<RadarChart>(R.id.radarChart)

        userIdTextView.text=myClass.userID

        val defaultPreferences = listOf(0f, 0f, 0f, 0f, 0f) // Replace with saved preferences if available
        setupRadarChart(radarChart, defaultPreferences)
        val default= User(myClass.userID, defaultPreferences[0],defaultPreferences[1],defaultPreferences[2],defaultPreferences[3],defaultPreferences[4] )

        loadUserInfo(myClass.userID)

        editProfileButton.setOnClickListener {
            showTastePreferencesDialog()
        }
    }
}

private fun setupRadarChart(radarChart: RadarChart, tastePreferences: List<Float>) {
    val entries = tastePreferences.map { RadarEntry(it) }
    val radarDataSet = RadarDataSet(entries, "Taste Preferences")
    val radarData = RadarData(radarDataSet)

    radarChart.data = radarData
    radarChart.animateXY(1000, 1000)
    configureRadarChartAppearance(radarChart)
    configureRadarDataSetAppearance(radarDataSet)
    radarChart.invalidate() // Refresh the chart
}


private fun configureRadarChartAppearance(radarChart: RadarChart) {
    radarChart.description.isEnabled = false
    radarChart.webLineWidth = 3f
    radarChart.webColor = Color.WHITE
    radarChart.webLineWidthInner = 3f
    radarChart.webColorInner = Color.WHITE
    radarChart.webAlpha = 200

    val xAxis = radarChart.xAxis
    xAxis.textSize = 20f
    xAxis.textColor=Color.WHITE
    xAxis.yOffset = 0f
    xAxis.xOffset = 0f
    xAxis.valueFormatter = IndexAxisValueFormatter(listOf("Dry", "Sour", "Sweet", "Smooth", "Hot"))

    val yAxis = radarChart.yAxis
    yAxis.setLabelCount(5, true)
    xAxis.textColor=Color.WHITE
    yAxis.textSize = 20f
    yAxis.axisMinimum = 0f
    yAxis.axisMaximum = 5f
}

private fun configureRadarDataSetAppearance(radarDataSet: RadarDataSet) {
    radarDataSet.color = Color.rgb(255, 255, 255)
    radarDataSet.fillAlpha = 180
    radarDataSet.lineWidth = 2f
    radarDataSet.isDrawHighlightCircleEnabled = true
    radarDataSet.valueTextColor = Color.WHITE
    radarDataSet.valueTextSize = 10f
    radarDataSet.setDrawHighlightIndicators(false)
    // 밝은 파란색 채우기 색상 설정
    val fillColor = Color.parseColor("#ADD8E6") // 밝은 파란색
    radarDataSet.fillColor = fillColor
    radarDataSet.setDrawFilled(true)
}


// … other functions …

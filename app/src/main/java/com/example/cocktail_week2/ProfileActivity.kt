package com.example.cocktail_week2

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
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

            loadUserInfo(dryPreference,sourPreference,sweetPreference,smoothPreference,hotPreference)

            dialog.dismiss()
        }

        dialog.show()
    }

    private fun loadUserInfo(pref1:Float, pref2:Float, pref3:Float, pref4:Float, pref5:Float) {

        val profEditUser=User(myClass.userID, pref1,pref2,pref3,pref4,pref5)
        apiService.editUserInfo(profEditUser).enqueue(object : Callback<Flavors> {
            override fun onResponse(
                call: Call<Flavors>,
                response: Response<Flavors>
            ) {
                if (response.isSuccessful) {
                    val user = response.body()
                    user?.let {
                        // 사용자 선호도 정보 사용

                        val flavorsList: List<Float> = Gson().fromJson(response.body()?.toString(), object : TypeToken<List<Float>>() {}.type)
                        val preferences = flavorsList.toList()
                        setupRadarChart(findViewById(R.id.radarChart), preferences)
                    }
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
    configureRadarChartAppearance(radarChart)
    configureRadarDataSetAppearance(radarDataSet)
    radarChart.invalidate() // Refresh the chart
}


private fun configureRadarChartAppearance(radarChart: RadarChart) {
    radarChart.description.isEnabled = false
    radarChart.webLineWidth = 1f
    radarChart.webColor = Color.LTGRAY
    radarChart.webLineWidthInner = 1f
    radarChart.webColorInner = Color.LTGRAY
    radarChart.webAlpha = 100

    val xAxis = radarChart.xAxis
    xAxis.textSize = 9f
    xAxis.yOffset = 0f
    xAxis.xOffset = 0f
    xAxis.valueFormatter = IndexAxisValueFormatter(listOf("Dry", "Sour", "Sweet", "Smooth", "Hot"))

    val yAxis = radarChart.yAxis
    yAxis.setLabelCount(5, true)
    yAxis.textSize = 9f
    yAxis.axisMinimum = 0f
    yAxis.axisMaximum = 5f
}

private fun configureRadarDataSetAppearance(radarDataSet: RadarDataSet) {
    radarDataSet.color = Color.rgb(103, 110, 129)
    radarDataSet.fillColor = Color.rgb(103, 110, 129)
    radarDataSet.setDrawFilled(true)
    radarDataSet.fillAlpha = 180
    radarDataSet.lineWidth = 2f
    radarDataSet.isDrawHighlightCircleEnabled = true
    radarDataSet.setDrawHighlightIndicators(false)
}


// … other functions …

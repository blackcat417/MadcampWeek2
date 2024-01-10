package com.example.cocktail_week2

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.RadarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

class ProfileActivity : AppCompatActivity() {


    private fun showTastePreferencesDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_taste_preferences, null)
        val dialog = AlertDialog.Builder(this)
            .setTitle("Set Your Taste Preferences")
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
            val dryPreference = sliderDry.progress
            val sourPreference = sliderSour.progress
            val sweetPreference = sliderSweet.progress
            val smoothPreference = sliderSmooth.progress
            val hotPreference = sliderHot.progress
            // Get values from other sliders
            // ...

            // TODO: Send these preferences to your server
            // ...

            dialog.dismiss()
        }

        dialog.show()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        supportActionBar?.hide()

        // ... other initializations ...
        val userIdTextView = findViewById<TextView>(R.id.topBar)
        val editProfileButton = findViewById<Button>(R.id.btnEditProfile)
        val profileImageView = findViewById<ImageView>(R.id.ivProfileImage)
        val cocktailCountTextView = findViewById<TextView>(R.id.tvCocktailCount)
        val radarChart = findViewById<RadarChart>(R.id.radarChart)
        editProfileButton.setOnClickListener {
            showTastePreferencesDialog()
        }

        setupRadarChart(radarChart)
    }

    }

    private fun setupRadarChart(radarChart: RadarChart) {
        val tastePreferences = listOf(3f, 4f, 2f, 5f, 3f) // Example ratings: dry, sour, sweet, smooth, hot

        val entries = tastePreferences.map { RadarEntry(it) }
        val radarDataSet = RadarDataSet(entries, "Taste Preferences")
        val radarData = RadarData(radarDataSet)

        radarChart.data = radarData
        configureRadarChartAppearance(radarChart)
        configureRadarDataSetAppearance(radarDataSet)
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

    // ... other functions ...

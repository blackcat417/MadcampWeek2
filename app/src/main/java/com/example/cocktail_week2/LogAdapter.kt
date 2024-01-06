package com.example.cocktail_week2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktail_week2.databinding.ItemImageLogBinding

class LogAdapter(private val imageList: List<ImageLog>) : RecyclerView.Adapter<LogAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemImageLogBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemImageLogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageLog = imageList[position]
        // 이미지와 관련 데이터를 뷰에 바인딩합니다.
    }

    override fun getItemCount() = imageList.size
}

package com.example.cocktail_week2.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cocktail_week2.LogEntry
import com.example.cocktail_week2.R

class AddAdapter(private val logs: List<LogEntry>) : RecyclerView.Adapter<AddAdapter.LogViewHolder>() {
    class LogViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // 뷰 홀더에 필요한 뷰 참조를 초기화합니다.
        val imageView: ImageView = view.findViewById(R.id.log_image)
        val dateView: TextView = view.findViewById(R.id.log_date)
        val nameView: TextView = view.findViewById(R.id.log_name)
        val descriptionView: TextView = view.findViewById(R.id.log_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.log_item, parent, false)
        return LogViewHolder(view)
    }

    override fun onBindViewHolder(holder: LogViewHolder, position: Int) {
        val log = logs[position]
        // 각 로그 항목에 데이터 설정
        Glide.with(holder.imageView.context)
            .load(log.imageUrl)
            .into(holder.imageView)

        holder.nameView.text = log.drinkName
        holder.descriptionView.text = log.instruction
    }

    override fun getItemCount() = logs.size
}

package com.example.cocktail_week2.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cocktail_week2.R
import com.example.cocktail_week2.LogCocktails

class LogAdapter(private var logList: List<LogCocktails>) : RecyclerView.Adapter<LogAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageViewCocktail)
        val nameTextView: TextView = view.findViewById(R.id.textViewName)
        val typeTextView: TextView = view.findViewById(R.id.textViewType)
        val descriptionTextView: TextView = view.findViewById(R.id.textViewDescription)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cocktail_item, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cocktail = logList[position]
        Glide.with(holder.imageView.context).load(cocktail.logImg).into(holder.imageView)
        holder.nameTextView.text = cocktail.logName
        holder.typeTextView.text = cocktail.logTime
        holder.descriptionTextView.text = cocktail.logRating.toString()
    }
    override fun getItemCount() = logList.size

    fun updateList(newLogs: List<LogCocktails>) {
        logList = newLogs
        notifyDataSetChanged()
    }

}
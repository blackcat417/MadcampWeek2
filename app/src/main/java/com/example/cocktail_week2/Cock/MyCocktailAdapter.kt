package com.example.cocktail_week2.Cock

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cocktail_week2.Cocktail
import com.example.cocktail_week2.MyCocktails
import com.example.cocktail_week2.R

class MyCocktailAdapter(private var myCocktailList: List<MyCocktails>) : RecyclerView.Adapter<MyCocktailAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageViewCocktail)
        val nameTextView: TextView = view.findViewById(R.id.textViewName)
        val typeTextView: TextView = view.findViewById(R.id.textViewType)
        val descriptionTextView: TextView = view.findViewById(R.id.textViewDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cocktail_item2, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cocktail = myCocktailList[position]
        Glide.with(holder.imageView.context).load(cocktail.strDrinkThumb).into(holder.imageView)
        holder.nameTextView.text = cocktail.strDrink
        holder.typeTextView.text = cocktail.strInstructions
        holder.descriptionTextView.text = cocktail.strIngredients
    }
    override fun getItemCount() = myCocktailList.size

    fun updateList(newLogs: List<MyCocktails>) {
        myCocktailList = newLogs
        notifyDataSetChanged()
    }
}

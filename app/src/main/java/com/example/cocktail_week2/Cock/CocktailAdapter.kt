package com.example.cocktail_week2.Cock

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cocktail_week2.Cocktail
import com.example.cocktail_week2.R

class CocktailAdapter(private var cocktailList: List<Cocktail>) : RecyclerView.Adapter<CocktailAdapter.ViewHolder>() {

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
        val cocktail = cocktailList[position]
        Glide.with(holder.imageView.context).load(cocktail.strDrinkThumb).into(holder.imageView)
        holder.nameTextView.text = cocktail.strDrink
        holder.typeTextView.text = cocktail.strCategory
        holder.descriptionTextView.text = cocktail.strIngredient1
    }

    override fun getItemCount() = cocktailList.size

    fun updateList(newItems: List<Cocktail>) {
        cocktailList = newItems
        notifyDataSetChanged()
    }

}

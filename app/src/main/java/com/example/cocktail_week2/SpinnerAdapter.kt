package com.example.cocktail_week2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat

class SpinnerAdapter(
    context: Context,
    resource: Int,
    objects: Array<String>
) : ArrayAdapter<String>(context, resource, objects) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createCustomView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createCustomView(position, convertView, parent)
    }

    private fun createCustomView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(context)
        val view = convertView ?: inflater.inflate(
            android.R.layout.simple_spinner_dropdown_item,
            parent,
            false
        )
        val textView = view.findViewById<TextView>(android.R.id.text1)

        // 아이템 데이터 설정
        val item = getItem(position)
        textView.text = item

        // 텍스트 색상 및 크기 변경
        textView.setTextColor(ContextCompat.getColor(context, R.color.black))
        textView.textSize = 18f  // 텍스트 크기를 18sp로 설정

        return view
    }
}

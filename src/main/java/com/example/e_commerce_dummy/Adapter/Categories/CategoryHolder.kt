package com.example.e_commerce_dummy.Adapter.Categories

import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerce_dummy.R

class CategoryHolder(itemView: View, itemclick: (position: Int) -> Unit) :
    RecyclerView.ViewHolder(itemView) {

    val button: Button

    init {
        this.button = itemView.findViewById(R.id.categoryBtn)
        this.button.setOnClickListener { _ ->
            itemclick(adapterPosition)
        }
    }
}
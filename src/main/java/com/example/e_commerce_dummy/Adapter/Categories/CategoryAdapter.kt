package com.example.e_commerce_dummy.Adapter.Categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerce_dummy.R

class CategoryAdapter(categoryList: ArrayList<Categories>, var itemclick: (position: Int)->Unit) : RecyclerView.Adapter<CategoryHolder>() {
    var categoryList = categoryList
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.btn_category,parent,false)

        return CategoryHolder(v, itemclick)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.button.text = categoryList.get(position).getCategoryName()
    }
}
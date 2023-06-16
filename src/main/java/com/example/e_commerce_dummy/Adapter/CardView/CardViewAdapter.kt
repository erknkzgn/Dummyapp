package com.example.e_commerce_dummy.Adapter.CardView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerce_dummy.Adapter.ViewPagerAdapter.ViewPagerAdapter
import com.example.e_commerce_dummy.DataOperations.Room.ss.DAO.Entity.Favorite
import com.example.e_commerce_dummy.DataOperations.Room.ss.DAO.Model.Product
import com.example.e_commerce_dummy.R
import com.example.e_commerce_dummy.databinding.ProductCardview2Binding


class CardViewAdapter(
                      var cardViewList: ArrayList<Product>,
                      var favList: ArrayList<Favorite>?,
                      var clickAction: (position: Int) -> Unit,
                      var clickProduct: (position: Int) -> Unit
) : RecyclerView.Adapter<CardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val itemBinding = ProductCardview2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardViewHolder(itemBinding, clickAction,clickProduct)
    }

    override fun getItemCount(): Int {
        return cardViewList.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        if (favList == null || favList!!.size == 0) {
            holder.favBtn.setImageResource(R.drawable.baseline_favorite)
        } else {
            if (favList!!.filter { it.id == cardViewList.get(position).id }.size == 1) {
                holder.favBtn.setImageResource(R.drawable.baseline_favorite_selected)
            } else {
                holder.favBtn.setImageResource(R.drawable.baseline_favorite)
            }
        }

        val viewPagerAdapter = ViewPagerAdapter(cardViewList[position].images)
        holder.ivImage.adapter = viewPagerAdapter


        //Picasso.get().load(cardViewList.get(position).images.get(0)).into(holder.ivImage)
        //holder.ivImage.setImageResource(R.drawable.ic_dummy_image)
        holder.tvDescription.text = cardViewList.get(position).description
        holder.tvTitle.text = cardViewList.get(position).title
        holder.priceTv.text = cardViewList.get(position).price + " TL"
        holder.ratingBar.rating = (cardViewList.get(position).rating.toFloat())
    }
}
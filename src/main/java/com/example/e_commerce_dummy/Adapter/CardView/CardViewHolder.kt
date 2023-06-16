package com.example.e_commerce_dummy.Adapter.CardView

import android.widget.ImageButton
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.e_commerce_dummy.databinding.ProductCardview2Binding

class CardViewHolder(
    private val binding: ProductCardview2Binding,
    clickAddFav: (position: Int) -> Unit,
    clickProduct: (position: Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    var ivImage: ViewPager2
    var tvTitle: TextView
    var tvDescription: TextView
    var favBtn: ImageButton
    val priceTv: TextView
    val ratingBar: RatingBar

    init {
        ivImage =
            binding.viewPager  //itemView.findViewById(R.id.ivProductImageCardView)
        tvTitle =
            binding.tvProductTitleCardView //itemView.findViewById(R.id.tvProductTitleCardView)
        tvDescription =
            binding.tvProductdescriptionCardView //binding.tvProductdescriptionCardView //itemView.findViewById(R.id.tvProductdescriptionCardView)
        priceTv = binding.priceTv
        favBtn = binding.imageAddFavoriteBtn
        ratingBar = binding.ratingBar

        favBtn.setOnClickListener { _ ->
            clickAddFav(adapterPosition)
        }
        binding.root.setOnClickListener{ _ ->
            clickProduct(adapterPosition)
        }

    }
}
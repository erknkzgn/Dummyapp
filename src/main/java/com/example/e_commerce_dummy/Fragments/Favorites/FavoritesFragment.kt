package com.example.e_commerce_dummy.Fragments.Favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.e_commerce_dummy.Adapter.CardView.CardViewAdapter
import com.example.e_commerce_dummy.DataOperations.Room.AppDatabase
import com.example.e_commerce_dummy.DataOperations.Room.ss.DAO.Entity.Favorite
import com.example.e_commerce_dummy.DataOperations.Room.ss.DAO.FavoriteDAO
import com.example.e_commerce_dummy.DataOperations.Room.ss.DAO.Model.Product
import com.example.e_commerce_dummy.databinding.FragmentFavoritesBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class FavoritesFragment(bottomNavigationView: BottomNavigationView) : Fragment() {

    private var bottomNav = bottomNavigationView
    private var favs: List<Favorite>? = null
    private var favList: ArrayList<Product> = ArrayList<Product>()
    private var _binding: FragmentFavoritesBinding? = null
    private var db: AppDatabase? = null
    private var favoriteDao: FavoriteDAO? = null
    private lateinit var cardViewAdapter: CardViewAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = AppDatabase.getFavoriteDatabase(view.context)
        favoriteDao = db!!.favoriteDao()

        var test = ArrayList<String>()
        test.add("ascas")

        favs = favoriteDao!!.getAll()


        /*for (i in favs!!){
            val product = Product(i.id,i.title,i.description,i.price,i.discountPercentage,i.rating,i.stock,i.brand,i.category,i.thumbnail,i.images)
            favList.add(product)
        }*/
        favList = favoriteListToProductArrayList(favs!!)

        setAdapter(view)
    }

    fun favoriteListToProductArrayList(list: List<Favorite>): ArrayList<Product> {
        var arrayList = ArrayList<Product>()
        for (i in list) {
            val product = Product(
                i.id,
                i.title,
                i.description,
                i.price,
                i.discountPercentage,
                i.rating,
                i.stock,
                i.brand,
                i.category,
                i.thumbnail,
                i.images
            )
            arrayList.add(product)
        }
        return arrayList
    }

    fun clickAction(position: Int) {
        favs = favoriteDao!!.getAll()
        favoriteDao!!.delete(favs!!.get(position))
        Toast.makeText(
            view?.context,
            favList.get(position).title + " will remove from favorites",
            Toast.LENGTH_SHORT
        ).show()
        cardViewAdapter.cardViewList = favoriteListToProductArrayList(favoriteDao!!.getAll())
        cardViewAdapter.notifyDataSetChanged()
    }
    private  fun clickProduct(position: Int){
        bottomNav.visibility = View.VISIBLE
        Toast.makeText(context,"product tıklandı",Toast.LENGTH_SHORT)
    }

    fun setAdapter(view: View) {
        cardViewAdapter = CardViewAdapter(favList, ArrayList(favs!!), this::clickAction,this::clickProduct)

        val cardViewLyt =
            binding.productListFavoritesLyt //view.findViewById<RecyclerView>(R.id.productListCategoriesLyt)
        cardViewLyt.adapter = cardViewAdapter

        val layoutManager = GridLayoutManager(view.context, 2)
        cardViewLyt.layoutManager = layoutManager
    }

}
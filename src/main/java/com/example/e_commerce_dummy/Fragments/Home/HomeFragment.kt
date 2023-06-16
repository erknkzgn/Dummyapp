package com.example.e_commerce_dummy.Fragments.Home

import android.R
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_commerce_dummy.Adapter.CardView.CardViewAdapter
import com.example.e_commerce_dummy.Adapter.Categories.Categories
import com.example.e_commerce_dummy.Adapter.Categories.CategoryAdapter
import com.example.e_commerce_dummy.DataOperations.Room.AppDatabase
import com.example.e_commerce_dummy.DataOperations.Room.ss.DAO.Entity.Favorite
import com.example.e_commerce_dummy.DataOperations.Room.ss.DAO.Model.Product
import com.example.e_commerce_dummy.Network.ApiResponseProduct
import com.example.e_commerce_dummy.Network.WebService
import com.example.e_commerce_dummy.databinding.FragmentHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private val cardViewList = ArrayList<Product>()
private val categoryList = ArrayList<Categories>()
private var db: AppDatabase? = null
private var favList: List<Favorite>? = null

class HomeFragment(bottomNavigationView: BottomNavigationView) : Fragment() {

    private var bottomNav = bottomNavigationView
    private lateinit var cardViewAdapter: CardViewAdapter
    private lateinit var categoryAdapter: CategoryAdapter
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    init {
        if (cardViewList.isNullOrEmpty()){
            getAllProducts()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupConfigurations()
        db = AppDatabase.getFavoriteDatabase(view.context)

        getCategories()
        //getAllProducts()
        setupAdapter(view)
    }

    private fun setupAdapter(view: View) {
        //Category
        categoryAdapter = CategoryAdapter(categoryList, this::categoryItemClick)

        val categoriesLyt = binding.categoriesLyt //view.findViewById<RecyclerView>(R.id.categoriesLyt)
        categoriesLyt.adapter = categoryAdapter

        val categoryLayoutManager = LinearLayoutManager(view.context)
        categoryLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        categoriesLyt.layoutManager = categoryLayoutManager


        //CardView
        val favoriteDao = db!!.favoriteDao()
        favList = favoriteDao.getAll()
        var arrayListFavorite = ArrayList(favList!!)

        cardViewAdapter = CardViewAdapter(cardViewList, arrayListFavorite, this::clickAddFav,this::clickProduct)

        val cardViewLyt = binding.productListCategoriesLyt //view.findViewById<RecyclerView>(R.id.productListCategoriesLyt)
        cardViewLyt.adapter = cardViewAdapter

        val layoutManager = GridLayoutManager(view.context, 2)
        cardViewLyt.layoutManager = layoutManager

    }

    private fun setupConfigurations() {
       searchFilter()
    }

    private fun categoryItemClick(position: Int) {
        Toast.makeText(
            view?.context,
            categoryList.get(position).getCategoryName(),
            Toast.LENGTH_SHORT
        ).show()
        categoryFilter(categoryList.get(position).getCategoryName())
        //TODO("implementation needs filtering operations")
    }

    private fun clickProduct(position: Int){
        bottomNav.visibility = View.GONE
        Toast.makeText(context,"product tıklandı "+ position ,Toast.LENGTH_SHORT).show()
    }
    private fun clickAddFav(position: Int) {

        val favoriteDao = db!!.favoriteDao()
        favList = favoriteDao.getAll()
        val favItem = cardViewAdapter.cardViewList.get(position)

        if (favList!!.filter { it.id == cardViewAdapter.cardViewList.get(position).id }.size == 1) {
            //Toast.makeText(view?.context, cardViewList.get(position).title + " removed favorites", Toast.LENGTH_SHORT).show()
            favoriteDao.delete(favList!!.filter { it.id == cardViewAdapter.cardViewList.get(position).id }.first())
        } else {
            //Toast.makeText(view?.context, cardViewList.get(position).title + " will added favorites", Toast.LENGTH_SHORT).show()
            favoriteDao.insertAll(favItem.convertToFavorite(favItem))
            /*favoriteDao.insertAll(
                Favorite(
                    favItem.id,
                    favItem.title,
                    favItem.description,
                    favItem.price,
                    favItem.discountPercentage,
                    favItem.rating,
                    favItem.stock,
                    favItem.brand,
                    favItem.category,
                    favItem.thumbnail,
                    favItem.images
                )
            )*/
        }
        cardViewAdapter.favList = ArrayList(favoriteDao.getAll())
        cardViewAdapter.notifyDataSetChanged()
    }

    private fun getRetrofitObject(baseUrl: String): Retrofit{
        return  Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getAllProducts(){
        val retrofit = getRetrofitObject("https://dummyjson.com/")
        val cagri = retrofit.create(WebService::class.java).ProductListGetir()

        cagri.enqueue(object : Callback<ApiResponseProduct> {
            override fun onResponse(call: Call<ApiResponseProduct>, response: Response<ApiResponseProduct>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        val apiResponse = it
                        for (product in apiResponse.products) {
                            cardViewList.add(product)
                        }
                        cardViewAdapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onFailure(call: Call<ApiResponseProduct>, t: Throwable) {
                Toast.makeText(context,"Hata -> "+t.toString(),Toast.LENGTH_LONG)
            }
        })
    }

    private fun searchFilter(){
        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s!!.length == 0){
                    cardViewAdapter.cardViewList = cardViewList
                }else{
                    cardViewAdapter.cardViewList = ArrayList(cardViewList.filter { it.brand.contains(s!!,true) })
                }
                cardViewAdapter.notifyDataSetChanged()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun getCategories(){
            val retrofit = getRetrofitObject("https://dummyjson.com/")
            var cagri = retrofit.create(WebService::class.java).GetCategories()

            cagri.enqueue(object : Callback<List<String>>{
                override fun onResponse(
                    call: Call<List<String>>,
                    response: Response<List<String>>
                ) {
                    if (response.isSuccessful){
                        response.body()?.let {
                            for (categoryname in it){
                                categoryList.add(Categories(categoryname))
                            }
                            categoryAdapter.notifyDataSetChanged()

                        }
                    }

                }

                override fun onFailure(call: Call<List<String>>, t: Throwable) {
                    println(t.message!!)
                }

            })

    }

    private fun categoryFilter(category: String){
        cardViewAdapter.cardViewList = ArrayList(cardViewList.filter { it.category == category })
        cardViewAdapter.notifyDataSetChanged()
    }

}





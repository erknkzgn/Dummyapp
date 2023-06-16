package com.example.e_commerce_dummy

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.example.e_commerce_dummy.databinding.ActivityMainBinding
import com.example.e_commerce_dummy.Fragments.Favorites.FavoritesFragment
import com.example.e_commerce_dummy.Fragments.Home.HomeFragment
import com.example.e_commerce_dummy.Fragments.ShoppingBasket.ShoppingBasketFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var bottomNav: BottomNavigationView
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupConfig()
    }

    override fun onUserInteraction() {
        super.onUserInteraction()
        this.currentFocus?.let { view ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
            view.clearFocus()
        }

    }

    private fun setupConfig() {
        supportActionBar?.hide()

        bottomNav = findViewById(R.id.bottomNav) as BottomNavigationView

        loadFragment(HomeFragment(bottomNav))
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.categories -> {
                    selectMenuItem(it.itemId,R.drawable.baseline_home_selected_24)
                    loadFragment(HomeFragment(bottomNav))
                    true
                }
                R.id.favorites -> {
                    selectMenuItem(it.itemId,R.drawable.baseline_favorite_selected)
                    loadFragment(FavoritesFragment(bottomNav))
                    bottomNav.visibility = View.GONE
                    true
                }
                R.id.basket -> {
                    selectMenuItem(it.itemId,R.drawable.baseline_shopping_basket_24)
                    loadFragment(ShoppingBasketFragment())
                    true
                }
                R.id.my_account -> {
                    selectMenuItem(it.itemId,R.drawable.baseline_account_circle_24)
                    loadFragment(ShoppingBasketFragment())
                    true
                }
                else -> {
                    false
                }
            }
        }

    }

    private fun selectMenuItem(menuItem: Int, icon: Int) {
        val array = mapOf(
            R.id.categories to R.drawable.outline_home_24,
            R.id.basket to R.drawable.outline_shopping_basket_24,
            R.id.favorites to R.drawable.baseline_favorite,
            R.id.my_account to R.drawable.outline_account_circle_24
        )

        for (i in array) {
            if (menuItem == i.key) {
                bottomNav.menu.findItem(i.key).setIcon(icon)
            }else{
                bottomNav.menu.findItem(i.key).setIcon(i.value)
            }

        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }

}
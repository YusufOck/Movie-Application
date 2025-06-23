// MainActivity: Uygulamanın ana giriş noktasıdır.
// Fragment'ları barındırır ve genel UI yönetimini sağlar.
package com.example.retrofit_usage.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.retrofit_usage.BuildConfig
import com.example.retrofit_usage.R
import com.example.retrofit_usage.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val ApiKey = BuildConfig.TMDB_API_KEY
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        setupActionBarWithNavController(navController)
        binding.bottomNavView.setupWithNavController(navController)

        binding.bottomNavView.setOnItemSelectedListener { item ->
            val navController = (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
            when (item.itemId) {
                R.id.mainFragment -> {
                    navController.navigate(R.id.mainFragment, null, androidx.navigation.NavOptions.Builder()
                        .setPopUpTo(R.id.mainFragment, true)
                        .build())
                    true
                }
                R.id.sonradanIzleFragment -> {
                    navController.navigate(R.id.sonradanIzleFragment)
                    true
                }
                else -> false
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        return navHostFragment.navController.navigateUp() || super.onSupportNavigateUp()
    }
}
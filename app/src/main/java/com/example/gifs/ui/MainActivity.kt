package com.example.gifs.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.gifs.R
import com.example.gifs.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navFragment: NavHostFragment = supportFragmentManager.findFragmentById(R.id.mainFragment) as NavHostFragment
        val navController = navFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)
    }
}
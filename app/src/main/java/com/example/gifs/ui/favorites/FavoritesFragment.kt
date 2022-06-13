package com.example.gifs.ui.favorites

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gifs.R
import com.example.gifs.databinding.FragmentFavoritesBinding

class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    companion object {
        fun newInstance() = FavoritesFragment()
    }

    private val viewModel by lazy {ViewModelProvider(this)[FavoritesViewModel::class.java] }
    private lateinit var bindingFavorites: FragmentFavoritesBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingFavorites = FragmentFavoritesBinding.inflate(inflater, container, false)
        return bindingFavorites.root
    }


}
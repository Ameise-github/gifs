package com.example.gifs.ui.search

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gifs.R
import com.example.gifs.databinding.FragmentSearchBinding

class SearchFragment : Fragment(R.layout.fragment_search) {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private val viewModel by lazy {ViewModelProvider(this).get(SearchViewModel::class.java)}
    private lateinit var bindingSearch: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingSearch = FragmentSearchBinding.inflate(inflater, container, false)
        return bindingSearch.root
    }
}
package com.example.gifs.ui.search

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView

import androidx.navigation.fragment.findNavController
import com.example.gifs.R
import com.example.gifs.databinding.FragmentSearchBinding
import com.example.gifs.ui.items.ClickListener
import com.example.gifs.ui.items.GifItem
import com.example.gifs.ui.items.RvItemGif
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class SearchFragment : Fragment(R.layout.fragment_search), ClickListener {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private val viewModel by lazy {ViewModelProvider(this).get(SearchViewModel::class.java)}
    private lateinit var bindingSearch: FragmentSearchBinding
    private val groupAdapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.state.observe(requireActivity(), ::applyState)
        bindingSearch = FragmentSearchBinding.inflate(inflater, container, false)
        return bindingSearch.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingSearch.rvSearch.adapter = groupAdapter

        bindingSearch.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.textSearch(query.orEmpty())
                return false
            }
        })
    }

    private fun applyState(state: SearchViewState) {
        groupAdapter.update(state.items.map { GifItem(it, this) }.toList())
    }

    override fun onItemClick(gif: RvItemGif) {
        findNavController().navigate(SearchFragmentDirections.actionSearchToDetails(gif))
    }
}
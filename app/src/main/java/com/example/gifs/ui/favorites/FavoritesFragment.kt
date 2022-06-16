package com.example.gifs.ui.favorites


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.gifs.R
import com.example.gifs.databinding.FragmentFavoritesBinding
import com.example.gifs.db.GifDatabase
import com.example.gifs.ui.home.HomeFragmentDirections
import com.example.gifs.ui.items.ClickListener
import com.example.gifs.ui.items.GifItem
import com.example.gifs.ui.items.RvItemGif
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class FavoritesFragment : Fragment(R.layout.fragment_favorites), ClickListener {

    companion object {
        fun newInstance() = FavoritesFragment()
    }

    private val viewModel:FavoritesViewModel by viewModels{
        FavoritesViewModel.Factory(
            GifDatabase
                .getInstance(requireActivity().application)
                .favoritesDao()
        )
    }
    private val groupAdapter = GroupAdapter<GroupieViewHolder>()

    private lateinit var bindingFavorites: FragmentFavoritesBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingFavorites = FragmentFavoritesBinding.inflate(inflater, container, false)

        viewModel.state.observe(requireActivity(), ::applyState)

        bindingFavorites.rvFavorites.adapter = groupAdapter
        viewModel.init()

        return bindingFavorites.root
    }

    private fun applyState(state: FavoritesViewState) {
        groupAdapter.addAll(state.items.map { GifItem(it, this) })
    }

    override fun onItemClick(gif: RvItemGif) {
        findNavController().navigate(HomeFragmentDirections.actionHomeToDetails(gif))
    }
}
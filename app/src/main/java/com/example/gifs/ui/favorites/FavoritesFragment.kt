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
        viewModel.state.observe(requireActivity(), ::applyState)
        bindingFavorites = FragmentFavoritesBinding.inflate(inflater, container, false)

        return bindingFavorites.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingFavorites.rvFavorites.adapter = groupAdapter
        viewModel.init()
    }

    private fun applyState(state: FavoritesViewState) {
        groupAdapter.update(state.items.map { GifItem(it, this) }.toList())
    }

    override fun onItemClick(gif: RvItemGif) {
        findNavController().navigate(FavoritesFragmentDirections.actionFavoritesToDetails(gif))
    }
}
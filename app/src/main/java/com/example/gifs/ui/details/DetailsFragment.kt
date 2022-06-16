package com.example.gifs.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.gifs.R
import com.example.gifs.databinding.FragmentDetailsBinding
import com.example.gifs.db.GifDatabase

class DetailsFragment : Fragment(R.layout.fragment_details) {

    companion object {
        fun newInstance() = DetailsFragment()
    }

    private val args by navArgs<DetailsFragmentArgs>()
    private lateinit var extGifId: String

    private val viewModel: DetailsViewModel by viewModels {
        DetailsViewModel.Factory(
            GifDatabase
                .getInstance(requireActivity().application)
                .favoritesDao()
        )
    }
    private lateinit var bindingDetails: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingDetails = FragmentDetailsBinding.inflate(inflater, container, false)

        extGifId = args.gifDetails.id

        viewModel.state.observe(requireActivity(), ::applyState)
        viewModel.getFavorites(extGifId)

        bindingDetails.gitTitle.text = args.gifDetails.title
        bindingDetails.imageUrl = args.gifDetails.url

        return bindingDetails.root
    }


    private fun applyState(state: DetailsViewState) {
        bindingDetails.isFavorites = state.isFavorites
        if (state.isFavorites) {
            // delete
            bindingDetails.bFavorites.setOnClickListener { viewModel.deleteFavorites(extGifId) }
        } else {
            //add
            bindingDetails.bFavorites.setOnClickListener {
                viewModel.addFavorites(
                    extGifId,
                    args.gifDetails.title,
                    args.gifDetails.url
                )
            }
        }
    }
}
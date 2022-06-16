package com.example.gifs.ui.details

import android.os.Bundle
import android.util.Log
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

        bindingDetails.lifecycleOwner = this    // для обновления данных в MutableLiveData
        bindingDetails.model = viewModel

        viewModel.init(args.gifDetails.title, args.gifDetails.url, args.gifDetails.id)
        viewModel.getFavorites()
        bindingDetails.bFavorites.setOnClickListener { viewModel.clickBFavorites() }

        return bindingDetails.root
    }

}
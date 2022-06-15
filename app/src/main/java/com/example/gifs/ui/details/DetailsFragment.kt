package com.example.gifs.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.gifs.R
import com.example.gifs.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment(R.layout.fragment_details) {

    companion object {
        fun newInstance() = DetailsFragment()
    }
    private val args by navArgs<DetailsFragmentArgs>()

    private val viewModel by activityViewModels<DetailsViewModel>()
    private lateinit var bindingDetails: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingDetails = FragmentDetailsBinding.inflate(inflater, container, false)

        viewModel.title = args.gifDetails.title
        //viewModel.urlText = "url: ${args.gifDetails.url}"
        viewModel.image = args.gifDetails.url
        viewModel.gifId = args.gifDetails.id
        bindingDetails.gif = viewModel

        return bindingDetails.root
    }
}
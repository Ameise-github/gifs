package com.example.gifs.ui.details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gifs.R
import com.example.gifs.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment(R.layout.fragment_details) {

    companion object {
        fun newInstance() = DetailsFragment()
    }

    private val viewModel by lazy { ViewModelProvider(this).get(DetailsViewModel::class.java) }
    private lateinit var bindingDetails: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingDetails = FragmentDetailsBinding.inflate(inflater, container, false)
        return bindingDetails.root
    }

}
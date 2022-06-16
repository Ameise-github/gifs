package com.example.gifs.ui.details

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.gifs.R

@BindingAdapter("bind:imageUrl")
fun loadImage(imageView: ImageView, v: String?) {
    Glide.with(imageView.context)
        .load(v)
        .fitCenter()
        .placeholder(R.drawable.ic_test1)
        .error(R.drawable.ic_error)
        .into(imageView)
}
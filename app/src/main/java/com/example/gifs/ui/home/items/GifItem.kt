package com.example.gifs.ui.home.items

import com.bumptech.glide.Glide
import com.example.gifs.R
import com.example.gifs.data.network.GifResponse
import com.example.gifs.databinding.ItemGifBinding
import com.xwray.groupie.databinding.BindableItem


class GifItem(private val gif: GifResponse) : BindableItem<ItemGifBinding>() {
    override fun getLayout() = R.layout.item_gif

    override fun bind(viewHolder: ItemGifBinding, position: Int) {
        Glide.with(viewHolder.root)
            .load(gif.url)
            .placeholder(R.drawable.ic_test1)
            .error(R.drawable.ic_error)
            .into(viewHolder.imgItemGif)
    }

}
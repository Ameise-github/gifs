package com.example.gifs.ui.items

import com.bumptech.glide.Glide
import com.example.gifs.R
import com.example.gifs.databinding.ItemGifBinding
import com.xwray.groupie.databinding.BindableItem


class GifItem(private val gif: RvItemGif, private val clickListener: ClickListener) : BindableItem<ItemGifBinding>() {
    override fun getLayout() = R.layout.item_gif

    override fun bind(viewHolder: ItemGifBinding, position: Int) {
        Glide.with(viewHolder.root)
            .load(gif.url)
            .override(200,200)
            .fitCenter()
            .placeholder(R.drawable.ic_test1)
            .error(R.drawable.ic_error)
            .into(viewHolder.imgItemGif)
        viewHolder.imgItemGif.setOnClickListener{ item ->
            clickListener.onItemClick(gif)
        }
    }
}

interface ClickListener {
    fun onItemClick(gif: RvItemGif)
}
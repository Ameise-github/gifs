package com.example.gifs.ui.home.items

import com.bumptech.glide.Glide
import com.example.gifs.BuildConfig
import com.example.gifs.R
import com.example.gifs.databinding.ItemGifBinding
import com.xwray.groupie.databinding.BindableItem


class GifItem(private val gif: RvItemGif) : BindableItem<ItemGifBinding>() {
    override fun getLayout() = R.layout.item_gif

    override fun bind(viewHolder: ItemGifBinding, position: Int) {
        Glide.with(viewHolder.root)
            .load(gif.url)
            .override(200,200)
            .fitCenter()
            .placeholder(R.drawable.ic_test1)
            .error(R.drawable.ic_error)
            .into(viewHolder.imgItemGif)
    }
}

class RvItemGif (
    val id: String,
    val url:String = BuildConfig.API_GIPHY_IMG_URL + id + BuildConfig.API_GIPHY_IMG_POSTFIX
)

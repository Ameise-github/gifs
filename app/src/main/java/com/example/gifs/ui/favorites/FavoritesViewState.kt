package com.example.gifs.ui.favorites

import com.example.gifs.ui.items.RvItemGif

data class FavoritesViewState(
    val items: List<RvItemGif> = emptyList()
)

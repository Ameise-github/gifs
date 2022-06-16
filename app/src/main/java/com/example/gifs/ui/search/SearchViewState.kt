package com.example.gifs.ui.search

import com.example.gifs.ui.items.RvItemGif

data class SearchViewState(
    val input: String = "",
    val items: List<RvItemGif> = emptyList(),
)

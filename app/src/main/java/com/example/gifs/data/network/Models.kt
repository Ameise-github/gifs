package com.example.gifs.data.network

import kotlinx.serialization.Serializable

@Serializable
data class GifResponse(
    val id: String,
    val url: String,
    val title: String
)
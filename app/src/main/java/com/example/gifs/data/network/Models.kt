package com.example.gifs.data.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GiphyResponse(
    val data : MutableList<GifResponse>,
    val pagination : PaginationResponse,
    val meta: MetaResponse
)
@Serializable
data class GifResponse(
    val id: String,
    @SerialName("embed_url")
    val url: String,
    val title: String
)

@Serializable
data class PaginationResponse (
    val offset: Int,
    @SerialName("total_count")
    val totalCount: Int,
    val count: Int
)

@Serializable
data class MetaResponse (
    val msg: String,
    val status: Int,
    @SerialName("response_id")
    val responseId: String
)

package com.example.gifs.data.network

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiGiphy {
    @GET("trending")
    fun getTrending(@Query("limit")limit: Int = 25, @Query("offset")offset: Int = 0): Single<GiphyResponse>

    // поиск gif  api.giphy.com/v1/gifs/search

    //получить конкретный api.giphy.com/v1/gifs/{gif_id}

    // получить список по id? api.giphy.com/v1/gifs  dis через зпт

}

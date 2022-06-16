package com.example.gifs.ui.items

import android.os.Parcelable
import com.example.gifs.BuildConfig
import kotlinx.parcelize.Parcelize

@Parcelize
class RvItemGif (
    val id: String,
    val url:String = BuildConfig.API_GIPHY_IMG_URL + id + BuildConfig.API_GIPHY_IMG_POSTFIX,
    val title: String
):Parcelable
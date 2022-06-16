package com.example.gifs.ui.search

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gifs.data.network.ClientGiphy
import com.example.gifs.ui.items.RvItemGif
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

import io.reactivex.rxjava3.schedulers.Schedulers


class SearchViewModel : ViewModel() {
    private val client = ClientGiphy()

    val state = MutableLiveData<SearchViewState>(SearchViewState())


    fun textSearch(query: String) {
        client.api.searchGif(query = query)
            .observeOn(Schedulers.io())
            .map {
                it.data.map { resp -> RvItemGif(resp.id, title = resp.title) }.toList()
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({gifs->
                updateState {
                it.copy(items = gifs, input = query)
            }}, Throwable::printStackTrace)

    }

    private fun updateState(update: (SearchViewState) -> SearchViewState) {
        Handler(Looper.getMainLooper()).post {
            state.value?.also {
                state.value = update(it)
            }
        }
    }
}
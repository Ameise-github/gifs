package com.example.gifs.ui.favorites

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gifs.db.favorites.FavoritesDao
import com.example.gifs.ui.items.RvItemGif
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FavoritesViewModel(val favoritesDao: FavoritesDao) : ViewModel() {
    @Suppress("UNCHECKED_CAST")
    class Factory(private val favoritesDao: FavoritesDao) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return FavoritesViewModel(favoritesDao) as T
        }
    }

    val state = MutableLiveData<FavoritesViewState>(FavoritesViewState())
    private val compositeDisposable = CompositeDisposable()

    fun init() {
        compositeDisposable.add(
            favoritesDao.getGifs()
                .observeOn(Schedulers.io())
                .map {
                    it.map { entity -> RvItemGif(entity.extId, title = entity.title) }.toList()
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { gifs ->
                    updateState {
                        it.copy(items = gifs)
                    }
                }
        )
    }


    private fun updateState(update: (FavoritesViewState) -> FavoritesViewState) {
        Handler(Looper.getMainLooper()).post {
            state.value?.also {
                state.value = update(it)
            }
        }
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
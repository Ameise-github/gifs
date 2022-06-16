package com.example.gifs.ui.details

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gifs.db.favorites.FavoritesDao
import com.example.gifs.db.favorites.FavoritesEntity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailsViewModel(val favoritesDao: FavoritesDao) : ViewModel() {

    @Suppress("UNCHECKED_CAST")
    class Factory(private val favoritesDao: FavoritesDao) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return DetailsViewModel(favoritesDao) as T
        }
    }

//    val state = MutableLiveData<DetailsViewState>()
    private val compositeDisposable = CompositeDisposable()
    val isFav: ObservableBoolean = ObservableBoolean(false)


    fun getFavorites(extGifId: String) {
        compositeDisposable.add(favoritesDao.getGif(extGifId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                isFav.set(true)
//                updateState { it.copy(isFavorites = true) }
            })
        Log.d("DetailsViewModel.getFavorites", "${ isFav.get()}")
    }


    fun addFavorites(extGifId: String, titleGif: String, urlGif: String) {
        compositeDisposable.add(favoritesDao.insertGif(FavoritesEntity(extId = extGifId, title = titleGif, url = urlGif))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                isFav.set(true)
//                updateState { it.copy(isFavorites = true) }
            })
//        Log.d("DetailsViewModel.addFavorites1", "${ isFav.get()}")
//        isFav.set(true)
        Log.d("DetailsViewModel.addFavorites2", "${ isFav.get()}")
    }

    fun deleteFavorites(extGifId: String) {
        compositeDisposable.delete(favoritesDao.delete(extGifId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                isFav.set(false)
//                updateState { it.copy(isFavorites = false) }
            })
        Log.d("DetailsViewModel.deleteFavorites", "${ isFav}")
    }

//    private fun updateState(update: (DetailsViewState) -> DetailsViewState) {
//        Handler(Looper.getMainLooper()).post {
//            state.value?.also {
//                state.value = update(it)
//            }
//        }
//    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

}
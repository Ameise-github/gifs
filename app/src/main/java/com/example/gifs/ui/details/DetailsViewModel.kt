package com.example.gifs.ui.details

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

    private val compositeDisposable = CompositeDisposable()
    var isFavorit = MutableLiveData(false)

    var titleGif: String = ""
    var imgUrl: String = ""
    private var extGifId: String = ""

    fun init(title: String, url: String, extId: String) {
        titleGif = title
        imgUrl = url
        extGifId = extId
    }

    fun getFavorites() {
        if (!extGifId.isEmpty()) {
            compositeDisposable.add(
                favoritesDao.getGif(extGifId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { isFavorit.value = true },
                        Throwable::printStackTrace
                    )
            )
        }
    }

    fun clickBFavorites() {
        if (isFavorit.value == true) {
            // delete
            deleteFavorites()
        } else {
            // add
            addFavorites()
        }
    }


    private fun addFavorites() {
        compositeDisposable.add(
            favoritesDao.insertGif(
                FavoritesEntity(
                    extId = extGifId,
                    title = titleGif,
                    url = imgUrl
                ))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { isFavorit.value = true },
                    Throwable::printStackTrace
                )
        )
    }

    private fun deleteFavorites() {
        compositeDisposable.delete(favoritesDao.delete(extGifId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                isFavorit.value = false
            })
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

}
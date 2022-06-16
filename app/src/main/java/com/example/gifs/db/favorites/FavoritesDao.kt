package com.example.gifs.db.favorites

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable

@Dao
abstract class FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertGif(gif: FavoritesEntity): Completable

    @Query("SELECT * FROM favorites")
    abstract fun getGifs(): Observable<List<FavoritesEntity>>

    @Query("SELECT * FROM favorites WHERE ext_id = :gifId")
    abstract fun getGif(gifId: String): Maybe<FavoritesEntity>

    @Query("select * from favorites where ext_id in (:gifIds)")
    abstract fun getAllByIds(gifIds: List<String>): Observable<List<FavoritesEntity>>

    @Query("DELETE FROM favorites WHERE ext_id = :gifId")
    abstract fun delete(gifId: String): Completable


}

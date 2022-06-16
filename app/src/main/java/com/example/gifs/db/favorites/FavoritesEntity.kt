package com.example.gifs.db.favorites

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.gifs.db.GifDatabase

@Entity(tableName = GifDatabase.FAVORITES_TABLE)
data class FavoritesEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID)
    var id: Int = 0,

    @ColumnInfo(name = COLUMN_EXT_ID)
    var extId: String = "",

    @ColumnInfo(name = COLUMN_TITLE)
    var title: String = "",

    @ColumnInfo(name = COLUMN_URL)
    var url: String = "",
) {

    companion object {
        const val COLUMN_ID = "id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_URL = "url"
        const val COLUMN_EXT_ID = "ext_id"
    }
}

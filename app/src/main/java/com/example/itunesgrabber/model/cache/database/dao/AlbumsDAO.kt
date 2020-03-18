package com.example.itunesgrabber.model.cache.database.dao

import androidx.annotation.Keep
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.itunesgrabber.model.remote.itunes.entity.AlbumItemEntity

/**
 * DAO Object to interact example data and different screen
 *
 * @author EvgenySamarin
 * @since 20200315 v1
 */
@Dao
@Keep
interface AlbumsDAO {
    /** @since 20200315 v1: Insert group of elements to table */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(item: List<AlbumItemEntity>)

    /** @since 20200315 v1: remove all items from table */
    @Query("DELETE FROM albums")
    suspend fun deleteAll()

    /** @since 20200315 v1: get all items from table */
    @Query("SELECT * FROM albums WHERE collectionId =:collectionId")
    suspend fun getItemById(collectionId: Int): AlbumItemEntity

}
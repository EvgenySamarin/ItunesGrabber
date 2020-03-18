package com.example.itunesgrabber.model.repository.itunes

import com.example.itunesgrabber.model.remote.core.Either
import com.example.itunesgrabber.model.remote.core.Failure
import com.example.itunesgrabber.model.remote.itunes.entity.AlbumItemEntity
import com.example.itunesgrabber.model.remote.itunes.entity.AlbumListEntity

/**
 * Interface implementation for interactions between remote source and ViewModel
 *
 * @author EvgenySamarin
 * @since 20200314 v1
 */
interface ITunesRepository {
    /** @since 20200318 v1: search albums by specific search string */
    fun searchITunesMusicAlbum(searchString: String): Either<Failure, AlbumListEntity>

    /** @since 20200318 v1: search cached album by specific identifier */
    suspend fun getCachedAlbumById(collectionId: Int): Either<Failure, AlbumItemEntity>
}
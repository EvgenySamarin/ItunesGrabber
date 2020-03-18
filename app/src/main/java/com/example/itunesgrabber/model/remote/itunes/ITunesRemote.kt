package com.example.itunesgrabber.model.remote.itunes

import com.example.itunesgrabber.model.remote.core.Either
import com.example.itunesgrabber.model.remote.core.Failure
import com.example.itunesgrabber.model.remote.itunes.entity.AlbumListEntity

/**
 * Interface to interaction with API
 *
 * @author EvgenySamarin
 * @since 20200314 v1
 */
interface ITunesRemote {
    /** @since 20200318 v1: search albums by specific search string */
    fun searchITunesMusicAlbum(searchString: String): Either<Failure, AlbumListEntity>
}
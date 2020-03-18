package com.example.itunesgrabber.model.repository.itunes

import com.example.itunesgrabber.model.cache.database.AppRoomDatabase
import com.example.itunesgrabber.model.remote.core.Either
import com.example.itunesgrabber.model.remote.core.Failure
import com.example.itunesgrabber.model.remote.core.onNext
import com.example.itunesgrabber.model.remote.itunes.ITunesRemote
import com.example.itunesgrabber.model.remote.itunes.entity.AlbumItemEntity
import com.example.itunesgrabber.model.remote.itunes.entity.AlbumListEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Implementation of the appropriate interface
 *
 * @author EvgenySamarin
 * @since 20200316 v1
 */
class ITunesRepositoryImpl @Inject constructor(
    private val remote: ITunesRemote,
    private val database: AppRoomDatabase
) : ITunesRepository {
    override fun searchITunesMusicAlbum(searchString: String): Either<Failure, AlbumListEntity> {
        CoroutineScope(Dispatchers.IO).launch {
            //clear database before filling the new data
            database.albumsDao().deleteAll()
        }
        return remote.searchITunesMusicAlbum(searchString).onNext {
            CoroutineScope(Dispatchers.IO).launch {
                database.albumsDao().insertList(it.results)
            }
        }
    }

    override suspend fun getCachedAlbumById(collectionId: Int): Either<Failure, AlbumItemEntity> {
        val result = database.albumsDao().getItemById(collectionId)
        return Either.Right(result)
    }
}
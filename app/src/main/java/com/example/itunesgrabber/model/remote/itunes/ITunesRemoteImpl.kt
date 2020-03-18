package com.example.itunesgrabber.model.remote.itunes

import com.example.itunesgrabber.model.remote.core.Either
import com.example.itunesgrabber.model.remote.core.Failure
import com.example.itunesgrabber.model.remote.core.Request
import com.example.itunesgrabber.model.remote.itunes.entity.AlbumListEntity
import com.example.itunesgrabber.model.remote.services.ApiService
import javax.inject.Inject

/**
 * Remote interface implementation
 *
 * @author EvgenySamarin
 * @since 20200314 v1
 */
class ITunesRemoteImpl @Inject constructor(
    private val request: Request,
    private val service: ApiService
) : ITunesRemote {
    override fun searchITunesMusicAlbum(searchString: String): Either<Failure, AlbumListEntity> =
        request.make(service.searchITunesMusicAlbum(createMapRequest(searchString))) {
            it
        }

    private fun createMapRequest(searchString: String): Map<String, String> {
        val map = HashMap<String, String>()
        map[ApiService.PARAM_TERM] = searchString
        return map
    }
}
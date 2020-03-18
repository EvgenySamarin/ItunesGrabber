package com.example.itunesgrabber.model.remote.services

import com.example.itunesgrabber.model.remote.itunes.entity.AlbumListEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * REST API retrofit interface. For request should use raw body parameters (if possible)
 *
 * @author EvgenySamarin
 * @since 20200314 v1
 */
interface ApiService {
    /**
     * Contains service endpoint and request parameters, where
     *
     * Endpoints must have following prefix:
     * - POST - to create something
     * - PUT - to update something
     * - GET - to get something
     * - DELETE - to delete something
     *
     * Parameters which would be used into request by different ways. All parameters must have
     * prefix __PARAM__
     *
     * @since 20200314 v1
     */
    companion object {
        const val GET_SEARCH_I_TUNES = "search"

        /** Required param for search
         * @since 20200314 v1: The URL-encoded text string you want to search for */
        const val PARAM_TERM = "term"

        /** Required param for search
         * The two-letter country code for the store you want to search in ISO
         *  code format. The search uses the default store front for the specified country
         *  @since 20200314 v1 */
        const val PARAM_COUNTRY = "country"

        /** The media type you want to search for. For example: movie. The default is all.
         * Available values:
         *
         * movie, podcast, music, musicVideo, audiobook, shortFilm, tvShow, software, ebook, all
         * @since 20200314 v1:  */
        const val PARAM_MEDIA = "media"

        /** The type of results you want returned, relative to the specified media type. 
         * For example: movieArtist for a movie media type search. The default is the track entity
         * associated with the specified media type.
         * @see <a href="https://developer.apple.com/library/archive/documentation/AudioVideo/Conceptual/iTuneSearchAPI/Searching.html#//apple_ref/doc/uid/TP40017632-CH5-SW2">
         *  list of available entities</a>
         * @since 20200314 v1 */
        const val PARAM_ENTITY = "entity"

        /** The attribute you want to search for in the stores, relative to the specified media type
         * @see <a href="https://developer.apple.com/library/archive/documentation/AudioVideo/Conceptual/iTuneSearchAPI/Searching.html#//apple_ref/doc/uid/TP40017632-CH5-SW3">
         *  list of available attributes</a>
         * @since 20200314 v1 */
        const val PARAM_ATTRIBUTE = "attribute"

        /** @since 20200314 v1: The number of search results you want the iTunes Store to return.
         * The default is 50. */
        const val PARAM_LIMIT = "limit"

        /** @since 20200314 v1: The language, English or Japanese, you want to use when returning
         * search results. The default is en_us (English) */
        const val PARAM_LANG = "lang"

        /** @since 20200314 v1: skip a specified number of results. Can be used for paging */
        const val PARAM_OFFSET = "offset"
    }

    /** @since 20200318 v1: search music albums by specific search string */
    @GET("$GET_SEARCH_I_TUNES?$PARAM_MEDIA=music&$PARAM_ENTITY=album")
    fun searchITunesMusicAlbum(@QueryMap params: Map<String,String>): Call<AlbumListEntity>
}
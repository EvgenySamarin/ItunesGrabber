package com.example.itunesgrabber.model.remote.itunes.entity

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

/**
 * Defines data of ITunes music album list
 *
 * @param resultCount
 * @param results list of albums
 *
 * @author EvgenySamarin
 * @since 20200314 v1
 */
@Keep
@Serializable
data class AlbumListEntity(
    val resultCount: Int,
    val results: List<AlbumItemEntity>
)

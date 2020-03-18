package com.example.itunesgrabber.model.remote.itunes.entity

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

/**
 * Defines data of ITunes music album
 *
 * @param wrapperType the name of the object returned by the search request
 * @param collectionType type of collection
 * @param artistId identifier of artist in ITunes storage
 * @param collectionId identifier of album in ITunes storage
 * @param artistName
 * @param collectionName canonical name of album
 * @param collectionCensoredName a production name of album
 * @param artistViewUrl link to artist web page in ITunes storage
 * @param collectionViewUrl link to album web page in ITunes storage
 * @param artworkUrl60 link to thumbs of album in 60x60 px dimension
 * @param artworkUrl100 link to thumbs of album in 100x100 px dimension
 * @param collectionPrice
 * @param collectionExplicitness whether there is abnormal vocabulary when i presume
 * @param trackCount
 * @param copyright album owner
 * @param country
 * @param currency
 * @param releaseDate
 * @param primaryGenreName genre of album music
 *
 * @author EvgenySamarin
 * @since 20200314 v1
 */
@Keep
@Serializable
@Entity(tableName = "albums")
data class AlbumItemEntity(
    val wrapperType: String,
    val collectionType: String,
    val artistId: Int,
    @PrimaryKey
    val collectionId: Int,
    val artistName: String,
    val collectionName: String,
    val collectionCensoredName: String,
    val artistViewUrl: String? = null,
    val collectionViewUrl: String,
    val artworkUrl60: String,
    val artworkUrl100: String,
    val collectionPrice: Float? = null,
    val collectionExplicitness: String,
    val trackCount: Int,
    val copyright: String,
    val country: String,
    val currency: String,
    val releaseDate: String,
    val primaryGenreName: String
)

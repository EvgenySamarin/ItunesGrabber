package com.example.itunesgrabber.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.itunesgrabber.model.remote.itunes.entity.AlbumItemEntity
import com.example.itunesgrabber.ui.viewmodel.core.BaseViewModel
import javax.inject.Inject

/**
 * ViewModel to bind album list items into adapter
 *
 * @author EvgenySamarin
 * @since 20200315 v1
 */
class ItemAlbumsViewModel @Inject constructor() : BaseViewModel() {
    private val artistName = MutableLiveData<String>()
    private val albumTitle = MutableLiveData<String>()
    private val albumThumbsUrl = MutableLiveData<String>()

    /** @since 20200318 v1: binds items field values with view model */
    fun bind(album: AlbumItemEntity) {
        artistName.value = album.artistName
        albumThumbsUrl.value = album.artworkUrl60
        albumTitle.value = album.collectionName
    }

    fun getArtistName(): MutableLiveData<String> = artistName
    fun getAlbumTitle(): MutableLiveData<String> = albumTitle
    fun getAlbumThumbsUrlTitle(): MutableLiveData<String> = albumThumbsUrl
}
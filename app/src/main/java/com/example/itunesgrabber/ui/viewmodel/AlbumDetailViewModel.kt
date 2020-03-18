package com.example.itunesgrabber.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.itunesgrabber.model.remote.itunes.entity.AlbumItemEntity
import com.example.itunesgrabber.model.repository.itunes.ITunesRepository
import com.example.itunesgrabber.ui.viewmodel.core.BaseViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

/**
 * For interaction View and Model for album detail info screen
 *
 * @author EvgenySamarin
 * @since 20200318 v1
 */
class AlbumDetailViewModel @Inject constructor(
    private val repository: ITunesRepository
) : BaseViewModel() {
    private val artworkUrl: MutableLiveData<String> = MutableLiveData()
    private val collectionName: MutableLiveData<String> = MutableLiveData()
    private val releaseDate: MutableLiveData<String> = MutableLiveData()
    private val primaryGenreName: MutableLiveData<String> = MutableLiveData()
    private val artistName: MutableLiveData<String> = MutableLiveData()
    private val artistViewUrl: MutableLiveData<String> = MutableLiveData()
    private val collectionViewUrl: MutableLiveData<String> = MutableLiveData()
    private val trackCount: MutableLiveData<String> = MutableLiveData()
    private val collectionPrice: MutableLiveData<String> = MutableLiveData()
    private val currency: MutableLiveData<String> = MutableLiveData()
    private val copyright: MutableLiveData<String> = MutableLiveData()

    fun getArtworkUrl(): MutableLiveData<String> = artworkUrl
    fun getCollectionName(): MutableLiveData<String> = collectionName
    fun getReleaseDate(): MutableLiveData<String> = releaseDate
    fun getPrimaryGenreName(): MutableLiveData<String> = primaryGenreName
    fun getArtistName(): MutableLiveData<String> = artistName
    fun getArtistViewUrl(): MutableLiveData<String> = artistViewUrl
    fun getCollectionViewUrl(): MutableLiveData<String> = collectionViewUrl
    fun getTrackCount(): MutableLiveData<String> = trackCount
    fun getCollectionPrice(): MutableLiveData<String> = collectionPrice
    fun getCurrency(): MutableLiveData<String> = currency
    fun getCopyright(): MutableLiveData<String> = copyright


    private var parentJob: Job = Job()

    /** @since 20200318 v1: get cached album by identifier */
    fun getCachedAlbum(collectionId: Int?) {
        fun handleSuccess(albumListEntity: AlbumItemEntity) {
            artworkUrl.postValue(albumListEntity.artworkUrl100)
            collectionName.postValue(albumListEntity.collectionName)
            releaseDate.postValue(albumListEntity.releaseDate)
            primaryGenreName.postValue(albumListEntity.primaryGenreName)
            artistName.postValue(albumListEntity.artistName)
            artistViewUrl.postValue(albumListEntity.artistViewUrl)
            collectionViewUrl.postValue(albumListEntity.collectionViewUrl)
            trackCount.postValue(albumListEntity.trackCount.toString())
            collectionPrice.postValue(albumListEntity.collectionPrice.toString())
            currency.postValue(albumListEntity.currency)
            copyright.postValue(albumListEntity.copyright)
        }

        collectionId?.let {
            unsubscribe()
            parentJob = Job()
            CoroutineScope(Dispatchers.Main + parentJob).launch {
                val result = withContext(Dispatchers.IO) {
                    repository.getCachedAlbumById(collectionId)
                }
                result.either(::handleError, ::handleSuccess)
            }
        }
    }

    /**
     * Функция отписки от работы. Отменяет выполнение корутины, знает о существовании корутины, т.к.
     * в корутине указана ссылка на Job
     */
    private fun unsubscribe() {
        parentJob.apply {
            cancelChildren()
            cancel()
        }
    }
}
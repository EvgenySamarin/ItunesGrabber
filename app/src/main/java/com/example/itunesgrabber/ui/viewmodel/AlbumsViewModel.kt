package com.example.itunesgrabber.ui.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.itunesgrabber.model.remote.itunes.entity.AlbumListEntity
import com.example.itunesgrabber.model.repository.itunes.ITunesRepository
import com.example.itunesgrabber.ui.view.home.fragments.adapters.AlbumsAdapter
import com.example.itunesgrabber.ui.viewmodel.core.BaseViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

/**
 *  For interaction View and Model for albums list screen
 *
 * @author EvgenySamarin
 * @since 20200316 v1
 */
class AlbumsViewModel @Inject constructor(
    private val repository: ITunesRepository
) : BaseViewModel() {
    val albumsAdapter = AlbumsAdapter()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val searchString: MutableLiveData<String> = MutableLiveData()

    init {
        searchString.value = "Poets"
        loadingVisibility.postValue(View.GONE)
    }

    private var parentJob: Job = Job()

    /** @since 20200318 v1: get album list from ITunes repository */
    fun onSearchClick() {
        errors.value = null
        searchAlbums()
    }

    private fun searchAlbums() {
        onLoadingStart()
        fun handleSuccess(albumListEntity: AlbumListEntity) {
            onLoadingFinished()
            albumListEntity.let {
                val sortedList = it.results.sortedBy { item ->
                    item.collectionName
                }
                albumsAdapter.updateAlbumsList(sortedList)
            }
        }

        searchString.value?.let { search ->
            unsubscribe()
            parentJob = Job()

            CoroutineScope(Dispatchers.Main + parentJob).launch {
                val result = withContext(Dispatchers.IO) {
                    repository.searchITunesMusicAlbum(search)
                }
                result.either({
                    onLoadingFinished()
                    handleError(it)
                }, ::handleSuccess)
            }
        }
    }

    private fun onLoadingStart() {
        loadingVisibility.value = View.VISIBLE
    }

    private fun onLoadingFinished() {
        loadingVisibility.value = View.GONE
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
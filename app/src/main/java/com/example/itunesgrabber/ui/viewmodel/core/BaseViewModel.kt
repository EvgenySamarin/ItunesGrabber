package com.example.itunesgrabber.ui.viewmodel.core

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.itunesgrabber.model.remote.core.Failure

/**
 * Common logic of view models here. Used for error handling
 *
 * @author EvgenySamarin
 * @since 20200318 v1
 */
abstract class BaseViewModel : ViewModel() {
    /** список ошибок сервера */
    var errors: MutableLiveData<String> = MutableLiveData()

    /** @since 20200318 v1: Error handling */
    protected fun handleError(failure: Failure) {
        when(failure){
            is Failure.NetworkConnectionError -> errors.postValue("Нет подключения к серверу")
            is Failure.NoContentError -> errors.postValue("Данных нет")
            else -> errors.postValue("Неизвестная ошибка")
        }
    }
}
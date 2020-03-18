package com.example.itunesgrabber.ui.viewmodel.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

/**
 * Responsible to create view model instances
 *
 * @param viewModels map contains pairs ViewModel classes (keys) and providers (values) like below
 * type: Map<Class<ViewModel>, Provider<ViewModel>>. Map was got from dagger generated view model
 * map
 *
 * @see <a href="https://medium.com/@vit.onix/dagger2-viewmodel-81d4cd59f642">
 * Как подружить Dagger2 и ViewModel</a>
 *
 * @author Виталий Шеянов edited by EvgenySamarin
 * @since 20180117 v1
 */
@Singleton
class ViewModelFactory @Inject constructor(
    private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {

    /**
     * Create view model implementation
     * get dagger provider from map creator and passes view model class implementation by its help
     *
     * @return ViewModel implementation special type
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModelProvider = viewModels[modelClass]
            ?: throw IllegalArgumentException("model class $modelClass not found")

        try {
            @Suppress("UNCHECKED_CAST")
            return viewModelProvider.get() as T
        } catch (e: Exception) {
            throw  RuntimeException(e)
        }
    }
}
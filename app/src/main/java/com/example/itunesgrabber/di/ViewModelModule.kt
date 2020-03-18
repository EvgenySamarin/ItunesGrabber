package com.example.itunesgrabber.di

import androidx.annotation.Keep
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.itunesgrabber.ui.viewmodel.AlbumDetailViewModel
import com.example.itunesgrabber.ui.viewmodel.AlbumsViewModel
import com.example.itunesgrabber.ui.viewmodel.HomeViewModel
import com.example.itunesgrabber.ui.viewmodel.ItemAlbumsViewModel
import com.example.itunesgrabber.ui.viewmodel.core.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Provides dependencies of view model
 *
 * @see <a href="https://medium.com/@vit.onix/dagger2-viewmodel-81d4cd59f642">
 * Как подружить Dagger2 и ViewModel</a>
 *
 * @author Виталий Шеянов edited by EvgenySamarin
 * @since 20200314 v1
 */
@Module
@Keep
abstract class ViewModelModule {
    /** @since 20200313 v1: bind view model factory */
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    /** bind HomeViewModel to map ViewModel on runtime */
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    /** bind AlbumsViewModel to map ViewModel on runtime */
    @Binds
    @IntoMap
    @ViewModelKey(AlbumsViewModel::class)
    abstract fun bindAlbumsViewModel(viewModel: AlbumsViewModel): ViewModel

    /** bind AlbumDetailViewModel to map ViewModel on runtime */
    @Binds
    @IntoMap
    @ViewModelKey(AlbumDetailViewModel::class)
    abstract fun bindAlbumDetailViewModel(viewModel: AlbumDetailViewModel): ViewModel

    /** bind ItemAlbumsViewModel to map ViewModel on runtime */
    @Binds
    @IntoMap
    @ViewModelKey(ItemAlbumsViewModel::class)
    abstract fun bindItemAlbumsViewModel(viewModel: ItemAlbumsViewModel): ViewModel
}
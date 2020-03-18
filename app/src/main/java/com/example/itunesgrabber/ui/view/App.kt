package com.example.itunesgrabber.ui.view

import androidx.annotation.Keep
import androidx.multidex.MultiDexApplication
import com.example.itunesgrabber.di.AppModule
import com.example.itunesgrabber.di.CacheModule
import com.example.itunesgrabber.di.RemoteModule
import com.example.itunesgrabber.di.ViewModelModule
import com.example.itunesgrabber.ui.view.home.HomeActivity
import com.example.itunesgrabber.ui.view.home.fragments.AlbumDetailFragment
import com.example.itunesgrabber.ui.view.home.fragments.AlbumsFragment
import dagger.Component
import timber.log.Timber
import javax.inject.Singleton

/**
 * The root file to initialize different libraries like Dagger, Timber etc. Also can contains
 * general app logic
 *
 * @author EvgenySamarin
 * @since 20200313 v1
 */
@Keep
class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree()) //enable timber logging
        initAppComponent() //create Dagger DI
    }

    private fun initAppComponent() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this)).build()
    }

    companion object {
        lateinit var appComponent: AppComponent
    }
}

/**
 * Base dependency injection class to provide different dependencies
 */
@Singleton
@Component(
    modules = [AppModule::class, CacheModule::class, RemoteModule::class, ViewModelModule::class]
)
interface AppComponent {
    //define classes for injection
    //activities
    fun inject(activity: HomeActivity)

    //fragments
    fun inject(fragment: AlbumDetailFragment)
    fun inject(fragment: AlbumsFragment)
}
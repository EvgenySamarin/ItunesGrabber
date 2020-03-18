package com.example.itunesgrabber.ui.view.core

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.example.itunesgrabber.R
import javax.inject.Inject

/**
 * Keep common logic for different activities
 *
 * @author EvgenySamarin
 * @since 20200315 v1
 */
abstract class BaseActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var appBarConfiguration: AppBarConfiguration

    /** @since 20200318 v1: show snackbar with action supported */
    abstract fun showSnackbar(message: String, action: () -> Unit)

    override fun onSupportNavigateUp(): Boolean =
        findNavController(R.id.nav_host_fragment).navigateUp(appBarConfiguration)

    /**
     * Inline func for simple providing ViewModel based on specific factory
     *
     * @author EvgenySamarin
     * @since 20200315 v1
     */
    inline fun <reified T : ViewModel> injectViewModel(): T {
        return ViewModelProvider(this, viewModelFactory)[T::class.java]
    }
}

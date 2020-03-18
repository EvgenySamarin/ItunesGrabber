package com.example.itunesgrabber.ui.view.home

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.itunesgrabber.R
import com.example.itunesgrabber.databinding.ActivityHomeBinding
import com.example.itunesgrabber.ui.view.App
import com.example.itunesgrabber.ui.view.core.BaseActivity
import com.example.itunesgrabber.ui.viewmodel.HomeViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * General activity
 *
 * @author EvgenySamarin
 * @since 20200315 v1
 */
class HomeActivity : BaseActivity() {
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
        homeViewModel = injectViewModel()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.viewModel = homeViewModel

        initNavigationController()
    }

    private fun initNavigationController() {
        setSupportActionBar(toolbar)
        val navController = nav_host_fragment.findNavController()
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun showSnackbar(message: String, action: () -> Unit) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_INDEFINITE).apply {
            setAction(R.string.ACTION_RETRY) {
                action()
                dismiss()
            }
            show()
        }
    }
}

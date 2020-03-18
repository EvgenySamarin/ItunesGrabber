package com.example.itunesgrabber.ui.view.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.itunesgrabber.ui.view.core.extension.startActivity
import com.example.itunesgrabber.ui.view.home.HomeActivity

/**
 * Responsible for show app foreground icon while base classes loading.
 * Can contains base logic of onboarding or sessions logic.
 *
 * @author EvgenySamarin
 * @since 20200312 v1
 */
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applicationContext.startActivity<HomeActivity>(newTask = true)
    }
}
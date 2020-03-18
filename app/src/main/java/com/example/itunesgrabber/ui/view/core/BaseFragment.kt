package com.example.itunesgrabber.ui.view.core

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.itunesgrabber.ui.view.core.extension.base
import javax.inject.Inject

/**
 *  Keep common logic for different fragments
 *
 * @author EvgenySamarin
 * @since 20200315 v1
 */
abstract class BaseFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    /**
     * Inline func for simple providing ViewModel based on specific factory
     *
     * @author EvgenySamarin
     * @since 20200315 v1
     */
    inline fun <reified T : ViewModel> Fragment.injectViewModel(): T {
        return ViewModelProvider(this, viewModelFactory)[T::class.java]
    }

    /**
     * Функция вызывает функцию из базового класса BaseActivity на уровне текущей реализации activity
     */
    inline fun base(block: BaseActivity.() -> Unit) {
        activity.base(block)
    }
}
package com.example.itunesgrabber.di

import androidx.annotation.Keep
import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * Provides annotation of view model for data binding. Create map key from ViewModel class in
 * runtime
 *
 * @see <a href="https://medium.com/@vit.onix/dagger2-viewmodel-81d4cd59f642">
 * Как подружить Dagger2 и ViewModel</a>
 *
 * @author Виталий Шеянов
 * @since 20180117 v1
 */
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
@Keep
annotation class ViewModelKey(val value: KClass<out ViewModel>)
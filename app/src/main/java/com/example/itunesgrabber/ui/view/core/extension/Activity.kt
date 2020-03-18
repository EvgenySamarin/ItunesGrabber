package com.example.itunesgrabber.ui.view.core.extension

import android.app.Activity
import com.example.itunesgrabber.ui.view.core.BaseActivity

/**
 * Extension функция, которая передает функцию body в тело активити BaseActivity, позволяя
 * вызывать ее методы.
 * @param block функция из BaseActivity.()
 */
inline fun Activity?.base(block: BaseActivity.() -> Unit) {
    (this as? BaseActivity)?.let(block)
}
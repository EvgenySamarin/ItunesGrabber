package com.example.itunesgrabber.ui.view.core.extension

import android.content.Context
import android.content.Intent
import android.os.Bundle

/**
 * Support function to avoid boilerplate code when u want to create intents.
 * Keep it simple and stupid (KISS). All the more enjoyable to read the code
 *
 * @param newTask sign to clean activity stack
 * @param args passing arguments
 * @param T name of target activity class
 */
inline fun <reified T> Context.startActivity(
    newTask: Boolean = false, args: Bundle? = null
) {
    this.startActivity(Intent(this, T::class.java).apply {
        if (newTask) {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        putExtra("args", args)
    })
}
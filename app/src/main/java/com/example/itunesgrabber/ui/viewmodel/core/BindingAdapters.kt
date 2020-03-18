package com.example.itunesgrabber.ui.viewmodel.core

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.itunesgrabber.R
import com.example.itunesgrabber.ui.view.core.extension.getParentActivity
import com.google.android.material.textfield.TextInputLayout

/** @since 20200315 v1: bind adapter to recycler view */
@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

/** @since 20200315 v1: bind liveData which responsible for view visibility */
@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && visibility != null) {
        visibility.observe(
            parentActivity,
            Observer { value -> view.visibility = value ?: View.VISIBLE })
    }
}

/** @since 20200315 v1: bind MutableLiveData to TextView and observe to it */
@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: MutableLiveData<String>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.text = value ?: "" })
    }
}

/** @since 20200315 v1: bind MutableLiveData<URL> to ImageView and observe to it */
@BindingAdapter("mutableUrl")
fun setMutableImageUrl(view: ImageView, text: MutableLiveData<String>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value ->
            Glide.with(view.context)
                .load(value ?: R.drawable.ic_img_stub)
                .apply(RequestOptions.circleCropTransform())
                .into(view)
        })
    }
}


/** @since 20200317 v1: handling web url with intent support */
@BindingAdapter("onClickToUrl")
fun bindMutableWebUrl(view: AppCompatImageButton, url: MutableLiveData<String>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && url != null) {
        url.observe(parentActivity, Observer {
            view.setOnClickListener { _ ->
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(it)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                parentActivity.applicationContext.startActivity(intent)
            }
        })
    }
}

/** @since 20200315 v1: bind action for search handling */
@BindingAdapter("onEndIconClick")
fun bindEndIconOnClickListener(view: TextInputLayout, onEndIconClick: () -> Unit) {
    view.setEndIconOnClickListener {
        onEndIconClick()
    }
}
package com.example.vinilos.adapter

import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import android.R
import com.squareup.picasso.Picasso

object BindingAdapterUtils {
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImage(view: ImageView, url: String?) {
        if (!url.isNullOrEmpty()) {
            Picasso.get().load(url).into(view)
        }
    }
}
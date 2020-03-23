package dev.brevitz.figurehunter.core.ui

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.load(url: String) {
    if (url.isNotBlank()) {
        Glide.with(this)
            .load(url)
            .into(this)
    }
}

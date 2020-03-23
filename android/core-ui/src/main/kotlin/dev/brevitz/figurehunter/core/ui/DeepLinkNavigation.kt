package dev.brevitz.figurehunter.core.ui

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.FragmentActivity

fun FragmentActivity.deepLink(url: String) {
    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
}

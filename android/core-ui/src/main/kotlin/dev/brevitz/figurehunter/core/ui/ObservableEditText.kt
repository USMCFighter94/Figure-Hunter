package dev.brevitz.figurehunter.core.ui

import androidx.annotation.StringRes
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.jakewharton.rxbinding2.widget.textChanges
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

fun TextInputEditText.showFieldError(@StringRes errorMessageId: Int) {
    (parent.parent as? TextInputLayout?)?.apply {
        isErrorEnabled = true
        error = context.getString(errorMessageId)
    }

    requestFocus()
}

fun TextInputEditText.observeTextChanges(): Observable<String> = textChanges()
    .doOnNext { (parent.parent as? TextInputLayout?)?.isErrorEnabled = false }
    .debounce(500, TimeUnit.MILLISECONDS)
    .map { it.toString() }

fun TextInputLayout.disableErrorIfShown() {
    if (isErrorEnabled) isErrorEnabled = false
}

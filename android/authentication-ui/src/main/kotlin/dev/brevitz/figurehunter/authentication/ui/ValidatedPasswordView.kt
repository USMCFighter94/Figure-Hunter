package dev.brevitz.figurehunter.authentication.ui

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dev.brevitz.figurehunter.core.ui.disableErrorIfShown
import dev.brevitz.figurehunter.core.ui.observeTextChanges
import dev.brevitz.figurehunter.core.ui.showFieldError
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class ValidatedPasswordView : TextInputLayout {
    val disposables = CompositeDisposable()

    val validPasswordObservable: Observable<Boolean>

    private val editText: TextInputEditText

    init {
        inflate(context, R.layout.view_validated_password, this)
            .also { editText = it.findViewById(R.id.validatedPasswordEditText) }

        validPasswordObservable = editText.observeTextChanges()
            .map { validPassword(it) }

        validPasswordObservable.skip(1)
            .doOnNext { disableErrorIfShown() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { validPassword ->
                if (!validPassword) {
                    editText.showFieldError(R.string.invalid_login_password)
                }
            }
            .addTo(disposables)
    }

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun getPassword() = editText.text.toString()
}

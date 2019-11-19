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

class ValidatedEmailView : TextInputLayout {
    val disposables = CompositeDisposable()

    val validEmailObservable: Observable<Boolean>

    private val editText: TextInputEditText

    init {
        inflate(context, R.layout.view_validated_email, this)
            .also { editText = it.findViewById(R.id.validatedEmailEditText) }

        validEmailObservable = editText.observeTextChanges()
            .map { validEmail(it) }

        validEmailObservable.skip(1)
            .doOnNext { disableErrorIfShown() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { validEmail ->
                if (!validEmail) {
                    editText.showFieldError(R.string.invalid_login_email)
                }
            }
            .addTo(disposables)
    }

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun getEmail() = editText.text.toString()
}

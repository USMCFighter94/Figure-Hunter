package dev.brevitz.figurehunter.authentication.ui

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import androidx.core.content.withStyledAttributes
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dev.brevitz.figurehunter.core.ui.disableErrorIfShown
import dev.brevitz.figurehunter.core.ui.observeTextChanges
import dev.brevitz.figurehunter.core.ui.showFieldError
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.PublishSubject

class ValidatedTextInputLayout : TextInputLayout {
    private val validDataSubject = PublishSubject.create<Boolean>()
    private val disposables = CompositeDisposable()

    private val editText: TextInputEditText

    val validDataObservable: Observable<Boolean> = validDataSubject.hide()

    init {
        inflate(context, R.layout.layout_validated_text_input_layout, this)
            .also { editText = it.findViewById(R.id.validatedEditText) }
    }

    constructor(context: Context) : super(context) {
        start(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        start(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        start(context, attrs)
    }

    fun setValidation(isValid: (String) -> Boolean) {
        editText.observeTextChanges()
            .map { isValid(it) }
            .subscribe { validDataSubject.onNext(it) }
            .addTo(disposables)
    }

    fun getData() = editText.text.toString()

    fun stop() {
        disposables.clear()
    }

    private fun start(context: Context, attrs: AttributeSet?) {
        var hint: Int? = null
        var errorMessage: Int = R.string.generic_validation_error
        var isPassword = false

        context.withStyledAttributes(attrs, R.styleable.ValidatedTextInputLayout) {
            hint = getResourceId(R.styleable.ValidatedTextInputLayout_hint, -1).takeIf { it > -1 }

            errorMessage = getResourceId(R.styleable.ValidatedTextInputLayout_errorMessage, -1).takeIf { it > -1 }
                ?: R.string.generic_validation_error

            isPassword = getBoolean(R.styleable.ValidatedTextInputLayout_isPassword, false)
        }

        hint?.let {
            editText.setHint(it)
        }

        if (isPassword) {
            editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }

        validDataObservable.skip(1)
            .doOnNext { disableErrorIfShown() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { validData ->
                if (!validData) {
                    editText.showFieldError(errorMessage)
                }
            }
            .addTo(disposables)
    }
}

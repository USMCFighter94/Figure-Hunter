package dev.brevitz.figurehunter.authentication.ui

import android.view.ViewGroup
import android.widget.LinearLayout
import com.airbnb.epoxy.EpoxyModelWithView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dev.brevitz.figurehunter.core.data.di.provideCoreComponent
import dev.brevitz.figurehunter.core.domain.RemoteData
import dev.brevitz.figurehunter.core.ui.disableErrorIfShown
import dev.brevitz.figurehunter.core.ui.inflateAs
import dev.brevitz.figurehunter.core.ui.observeTextChanges
import dev.brevitz.figurehunter.core.ui.showFieldError
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.view_login.view.*
import timber.log.Timber
import javax.inject.Inject

data class LoginView(private val goToRegister: () -> Unit) : EpoxyModelWithView<LinearLayout>() {
    @Inject
    lateinit var viewModel: LoginViewModel

    private val disposables = CompositeDisposable()
    private var emailEditText: TextInputEditText? = null
    private var emailInputLayout: TextInputLayout? = null

    private var passwordEditText: TextInputEditText? = null
    private var passwordInputLayout: TextInputLayout? = null

    private var submitButton: MaterialButton? = null

    override fun bind(view: LinearLayout) {
        super.bind(view)
        with(view) {
            emailEditText = loginEmailEditText
            emailInputLayout = loginEmailInputLayout

            passwordEditText = loginPasswordEditText
            passwordInputLayout = loginPasswordInputLayout

            submitButton = loginSubmitButton

            loginSubmitButton.setOnClickListener {
                viewModel.login(emailEditText!!.text.toString(), passwordEditText!!.text.toString())
            }

            newHere.setOnClickListener { goToRegister() }
        }
    }

    override fun buildView(parent: ViewGroup): LinearLayout {
        if (!::viewModel.isInitialized) {
            parent.context.provideCoreComponent().let {
                it.componentManager().getOrCreate(AuthenticationComponent.KEY) {
                    DaggerAuthenticationComponent.builder()
                        .coreComponent(it)
                        .build()
                }.inject(this)
            }
        }

        return parent.inflateAs(R.layout.view_login)
    }

    override fun onViewAttachedToWindow(view: LinearLayout) {
        super.onViewAttachedToWindow(view)

        viewModel.observe()
            .subscribe {
                when (it) {
                    is RemoteData.Success -> {

                    }

                    is RemoteData.Error -> Timber.e("Error logging in: %s", it.error)
                }
            }
            .addTo(disposables)

        val emailObservable = emailEditText?.observeTextChanges()
            ?.map { validEmail(it) }

        emailObservable?.skip(1)
            ?.doOnNext { emailInputLayout?.disableErrorIfShown() }
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe { validEmail ->
                if (!validEmail) {
                    emailEditText?.showFieldError(R.string.invalid_login_email)
                }
            }
            ?.addTo(disposables)

        val passwordObservable = passwordEditText?.observeTextChanges()
            ?.map { validPassword(it) }

        passwordObservable?.skip(1)
            ?.doOnNext { passwordInputLayout?.disableErrorIfShown() }
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe { validPassword ->
                if (!validPassword) {
                    passwordEditText?.showFieldError(R.string.invalid_login_password)
                }
            }
            ?.addTo(disposables)

        if (emailObservable != null && passwordObservable != null) {
            Observables.combineLatest(emailObservable, passwordObservable) { email, password -> email && password }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { validFields -> submitButton?.isEnabled = validFields }
                .addTo(disposables)
        }
    }

    override fun onViewDetachedFromWindow(view: LinearLayout) {
        super.onViewDetachedFromWindow(view)
        disposables.clear()
    }

    companion object {
        const val ID = "LoginView"
    }
}

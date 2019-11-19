package dev.brevitz.figurehunter.authentication.ui.login

import android.view.ViewGroup
import android.widget.LinearLayout
import com.airbnb.epoxy.EpoxyModelWithView
import com.google.android.material.button.MaterialButton
import dev.brevitz.figurehunter.authentication.ui.AuthenticationComponent
import dev.brevitz.figurehunter.authentication.ui.DaggerAuthenticationComponent
import dev.brevitz.figurehunter.authentication.ui.R
import dev.brevitz.figurehunter.authentication.ui.ValidatedEmailView
import dev.brevitz.figurehunter.authentication.ui.ValidatedPasswordView
import dev.brevitz.figurehunter.core.data.di.provideCoreComponent
import dev.brevitz.figurehunter.core.domain.RemoteData
import dev.brevitz.figurehunter.core.ui.inflateAs
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.view_login.view.*
import timber.log.Timber
import javax.inject.Inject

data class LoginView(private val goToRegister: () -> Unit) : EpoxyModelWithView<LinearLayout>() {
    @Inject
    internal lateinit var viewModel: LoginViewModel

    private val disposables = CompositeDisposable()

    private var emailView: ValidatedEmailView? = null
    private var passwordView: ValidatedPasswordView? = null
    private var submitButton: MaterialButton? = null

    override fun bind(view: LinearLayout) {
        super.bind(view)
        with(view) {
            emailView = view.findViewById(R.id.loginEmailView)
            passwordView = view.findViewById(R.id.loginPasswordView)
            submitButton = view.findViewById(R.id.loginSubmitButton)

            submitButton?.setOnClickListener {
                viewModel.login(emailView!!.getEmail(), loginPasswordView!!.getPassword())
            }

            newHere.setOnClickListener { goToRegister() }
        }

        viewModel.observe()
            .subscribe {
                when (it) {
                    is RemoteData.Success -> {

                    }

                    is RemoteData.Error -> Timber.e("Error logging in: %s", it.error)
                }
            }
            .addTo(disposables)

        if (emailView != null && passwordView != null) {
            Observables.combineLatest(
                emailView!!.validEmailObservable,
                passwordView!!.validPasswordObservable
            ) { email, password -> email && password }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { validFields -> submitButton?.isEnabled = validFields }
                .addTo(disposables)
        }
    }

    override fun unbind(view: LinearLayout) {
        super.unbind(view)
        disposables.clear()
        emailView?.disposables?.clear()
        passwordView?.disposables?.clear()
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

    companion object {
        const val ID = "LoginView"
    }
}

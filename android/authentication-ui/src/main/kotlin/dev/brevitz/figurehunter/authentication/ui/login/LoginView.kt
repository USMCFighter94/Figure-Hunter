package dev.brevitz.figurehunter.authentication.ui.login

import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import com.airbnb.epoxy.EpoxyModel
import com.google.android.material.button.MaterialButton
import com.squareup.phrase.Phrase
import dev.brevitz.figurehunter.authentication.ui.AuthenticationComponent
import dev.brevitz.figurehunter.authentication.ui.DaggerAuthenticationComponent
import dev.brevitz.figurehunter.authentication.ui.R
import dev.brevitz.figurehunter.authentication.ui.ValidatedTextInputLayout
import dev.brevitz.figurehunter.authentication.ui.validEmail
import dev.brevitz.figurehunter.authentication.ui.validPassword
import dev.brevitz.figurehunter.core.data.di.provideCoreComponent
import dev.brevitz.figurehunter.core.domain.RemoteData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.view_login.view.*
import timber.log.Timber
import javax.inject.Inject

data class LoginView(private val goToRegister: () -> Unit, private val finished: () -> Unit) : EpoxyModel<LinearLayout>() {

    @Inject
    internal lateinit var viewModel: LoginViewModel

    private var emailLayout: ValidatedTextInputLayout? = null
    private var passwordLayout: ValidatedTextInputLayout? = null
    private var submitButton: MaterialButton? = null
    private var loadingView: ProgressBar? = null

    override fun bind(view: LinearLayout) {
        super.bind(view)
        if (!::viewModel.isInitialized) {
            view.context.provideCoreComponent().let {
                it.componentManager().getOrCreate(AuthenticationComponent.KEY) {
                    DaggerAuthenticationComponent.builder()
                        .coreComponent(it)
                        .build()
                }.inject(this)
            }
        }

        with(view) {
            emailLayout = findViewById(R.id.loginEmailLayout)
            passwordLayout = findViewById(R.id.loginPasswordLayout)
            submitButton = findViewById(R.id.loginSubmitButton)
            loadingView = findViewById(R.id.loginLoadingView)

            submitButton?.setOnClickListener {
                viewModel.login(emailLayout!!.getData(), passwordLayout!!.getData())
            }

            newHere.setOnClickListener { goToRegister() }
        }

        emailLayout?.setValidation { validEmail(it) }
        passwordLayout?.setValidation { validPassword(it) }

        viewModel.observe()
            .distinctUntilChanged()
            .doOnNext { loadingView?.isVisible = it.isLoading() }
            .subscribe {
                when (it) {
                    is RemoteData.Success -> finished()
                    is RemoteData.Error -> {
                        Timber.e("Error logging in: %s", it.error)

                        val errorMessage = Phrase.from(view.context, R.string.login_failed_message)
                            .put("error", it.error.getUserError())
                            .format()

                        AlertDialog.Builder(view.context)
                            .setTitle(R.string.login_failed_title)
                            .setMessage(errorMessage)
                            .setPositiveButton(android.R.string.ok) { dialog, _ -> dialog.dismiss() }
                            .create()
                            .show()
                    }
                }
            }
            .addTo(viewModel.disposables)

        if (emailLayout != null && passwordLayout != null) {
            Observables.combineLatest(
                emailLayout!!.validDataObservable,
                passwordLayout!!.validDataObservable
            ) { email, password -> email && password }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { validFields -> submitButton?.isEnabled = validFields }
                .addTo(viewModel.disposables)
        }
    }

    override fun unbind(view: LinearLayout) {
        super.unbind(view)
        viewModel.stop()
        emailLayout?.stop()
        passwordLayout?.stop()
    }

    override fun getDefaultLayout() = R.layout.view_login

    companion object {
        const val ID = "LoginView"
    }
}

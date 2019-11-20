package dev.brevitz.figurehunter.authentication.ui.register

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
import kotlinx.android.synthetic.main.view_register.view.*
import timber.log.Timber
import javax.inject.Inject

data class RegisterView(private val goToLogin: () -> Unit, private val finished: () -> Unit) : EpoxyModel<LinearLayout>() {

    @Inject
    internal lateinit var viewModel: RegisterViewModel

    private var firstNameLayout: ValidatedTextInputLayout? = null
    private var lastNameLayout: ValidatedTextInputLayout? = null
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
            firstNameLayout = findViewById(R.id.registerFirstNameLayout)
            lastNameLayout = findViewById(R.id.registerFirstNameLayout)
            emailLayout = findViewById(R.id.registerEmailLayout)
            passwordLayout = findViewById(R.id.registerPasswordLayout)
            submitButton = findViewById(R.id.registerSubmitButton)
            loadingView = findViewById(R.id.registerLoadingView)

            alreadyHaveAccount.setOnClickListener { goToLogin() }
        }

        firstNameLayout?.setValidation { it.isNotBlank() }
        lastNameLayout?.setValidation { it.isNotBlank() }
        emailLayout?.setValidation { validEmail(it) }
        passwordLayout?.setValidation { validPassword(it) }

        submitButton?.setOnClickListener {
            viewModel.register(
                firstNameLayout!!.getData(),
                lastNameLayout!!.getData(),
                emailLayout!!.getData(),
                passwordLayout!!.getData()
            )
        }

        viewModel.observe()
            .distinctUntilChanged()
            .doOnNext { loadingView?.isVisible = it.isLoading() }
            .subscribe {
                when (it) {
                    is RemoteData.Success -> finished()
                    is RemoteData.Error -> {
                        Timber.e("Error registering: %s", it.error)

                        val errorMessage = Phrase.from(view.context, R.string.register_failed_message)
                            .put("error", it.error.getUserError())
                            .format()

                        AlertDialog.Builder(view.context)
                            .setTitle(R.string.register_failed_title)
                            .setMessage(errorMessage)
                            .setPositiveButton(android.R.string.ok) { dialog, _ -> dialog.dismiss() }
                            .create()
                            .show()
                    }
                }
            }
            .addTo(viewModel.disposables)

        if (firstNameLayout != null && lastNameLayout != null && emailLayout != null && passwordLayout != null) {
            Observables.combineLatest(
                firstNameLayout!!.validDataObservable,
                lastNameLayout!!.validDataObservable,
                emailLayout!!.validDataObservable,
                passwordLayout!!.validDataObservable
            ) { first, last, email, password -> first && last && email && password }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { validFields -> submitButton?.isEnabled = validFields }
                .addTo(viewModel.disposables)
        }
    }

    override fun getDefaultLayout() = R.layout.view_register

    companion object {
        const val ID = "RegisterView"
    }
}

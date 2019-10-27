package dev.brevitz.figurehunter.authentication.ui

import dev.brevitz.figurehunter.authentication.domain.AuthenticationRepository
import dev.brevitz.figurehunter.authentication.domain.LoginData
import dev.brevitz.figurehunter.authentication.domain.Token
import dev.brevitz.figurehunter.core.domain.RemoteData
import dev.brevitz.figurehunter.core.domain.RemoteError
import dev.brevitz.figurehunter.core.ui.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val repository: AuthenticationRepository
) : ViewModel<RemoteData<Token, RemoteError>>(RemoteData.NotAsked) {
    fun login(email: String, password: String) {
        repository.login(LoginData(email, password))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                { newState -> updateState { newState } },
                { error -> updateState { RemoteData.error(RemoteError.ParsingError(error)) } }
            )
            .addTo(disposables)
    }
}

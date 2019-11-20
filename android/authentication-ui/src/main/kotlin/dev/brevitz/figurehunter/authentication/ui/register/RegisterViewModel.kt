package dev.brevitz.figurehunter.authentication.ui.register

import dev.brevitz.figurehunter.authentication.domain.AuthenticationRepository
import dev.brevitz.figurehunter.authentication.domain.RegisterData
import dev.brevitz.figurehunter.core.domain.RemoteData
import dev.brevitz.figurehunter.core.domain.RemoteError
import dev.brevitz.figurehunter.core.domain.Storage
import dev.brevitz.figurehunter.core.domain.StorageKey
import dev.brevitz.figurehunter.core.domain.doIfSuccess
import dev.brevitz.figurehunter.core.domain.mapSuccess
import dev.brevitz.figurehunter.core.ui.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

internal class RegisterViewModel @Inject constructor(
    private val repository: AuthenticationRepository,
    private val storage: Storage
) : ViewModel<RemoteData<RegisterViewModel.Registered, RemoteError>>(RemoteData.NotAsked) {
    fun register(firstName: String, lastName: String, email: String, password: String) {
        repository.register(RegisterData(firstName, lastName, email, password))
            .doIfSuccess { storage.setString(StorageKey.TOKEN, it.token) }
            .map { data -> data.mapSuccess { Registered } }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                { newState -> updateState { newState } },
                { error -> updateState { RemoteData.error(RemoteError.ParsingError(error)) } }
            )
            .addTo(disposables)
    }

    object Registered
}

package dev.brevitz.figurehunter.account.ui

import dev.brevitz.figurehunter.account.domain.AccountRepository
import dev.brevitz.figurehunter.account.domain.User
import dev.brevitz.figurehunter.core.domain.Option
import dev.brevitz.figurehunter.core.domain.RemoteData
import dev.brevitz.figurehunter.core.domain.RemoteError
import dev.brevitz.figurehunter.core.domain.Storage
import dev.brevitz.figurehunter.core.domain.StorageKey
import dev.brevitz.figurehunter.core.ui.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AccountViewModel @Inject constructor(
    private val repository: AccountRepository,
    private val storage: Storage
) : ViewModel<AccountViewModel.State>(State()) {

    fun start() {
        repository.getUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { user -> updateState { it.copy(userData = user) } },
                { throwable -> updateState { it.copy(userData = RemoteData.error(RemoteError.ParsingError(throwable))) } }
            )
            .addTo(disposables)

        storage.observeString(StorageKey.TOKEN)
            .subscribeOn(Schedulers.io())
            .subscribe { option ->
                when (option) {
                    is Option.Some -> updateState { it.copy(loggedIn = true) }
                    else -> updateState { it.copy(loggedIn = false) }
                }
            }
            .addTo(disposables)
    }

    data class State(
        val userData: RemoteData<User, RemoteError> = RemoteData.NotAsked,
        val loggedIn: Boolean = false
    )
}

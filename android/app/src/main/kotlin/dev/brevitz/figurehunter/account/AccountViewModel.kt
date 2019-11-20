package dev.brevitz.figurehunter.account

import dev.brevitz.figurehunter.core.domain.Option
import dev.brevitz.figurehunter.core.domain.Storage
import dev.brevitz.figurehunter.core.domain.StorageKey
import dev.brevitz.figurehunter.core.ui.ViewModel
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AccountViewModel @Inject constructor(
    private val storage: Storage
) : ViewModel<AccountViewModel.State>(State()) {

    fun start() {
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
        val loggedIn: Boolean = false
    )
}

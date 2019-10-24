package dev.brevitz.figurehunter.home

import dev.brevitz.core.domain.RemoteData
import dev.brevitz.core.domain.RemoteError
import dev.brevitz.core.domain.home.Figure
import dev.brevitz.core.domain.home.HomeRepository
import dev.brevitz.figurehunter.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel<RemoteData<List<Figure>, RemoteError>>(RemoteData.NotAsked) {
    fun start() {
        repository.getFigures()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { newState -> updateState { newState } },
                { error -> updateState { RemoteData.error(RemoteError.ParsingError(error)) } }
            )
            .addTo(disposables)
    }
}

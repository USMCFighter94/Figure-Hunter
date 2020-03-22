package dev.brevitz.figurehunter.home.data

import dev.brevitz.figurehunter.core.data.doIfSuccess
import dev.brevitz.figurehunter.core.data.mapToRemoteData
import dev.brevitz.figurehunter.core.data.syncIfEmpty
import dev.brevitz.figurehunter.core.domain.ObservableRemoteData
import dev.brevitz.figurehunter.core.domain.RemoteData
import dev.brevitz.figurehunter.core.domain.RemoteError
import dev.brevitz.figurehunter.home.domain.Figure
import dev.brevitz.figurehunter.home.domain.HomeRepository
import dev.brevitz.figurehunter.core.domain.toMaybeError
import io.reactivex.Maybe

class HomeDataRepository(private val store: HomeStore, private val api: HomeApi) : HomeRepository {
    override fun getFigures(): ObservableRemoteData<List<Figure>> =
        store.get(HomeStoreKey)
            .syncIfEmpty(fetchFigures())
            .startWith(RemoteData.Loading)

    private fun fetchFigures(): Maybe<RemoteError> =
        api.getFigures()
            .mapToRemoteData { list -> list?.map { it.toDomain() } }
            .doIfSuccess { store.store(it) }
            .flatMapMaybe { it.toMaybeError() }
}

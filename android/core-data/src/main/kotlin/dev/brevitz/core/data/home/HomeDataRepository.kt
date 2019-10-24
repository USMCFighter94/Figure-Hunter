package dev.brevitz.core.data.home

import dev.brevitz.core.data.doIfSuccess
import dev.brevitz.core.data.mapToRemoteData
import dev.brevitz.core.data.syncIfEmpty
import dev.brevitz.core.domain.ObservableRemoteData
import dev.brevitz.core.domain.RemoteError
import dev.brevitz.core.domain.home.Figure
import dev.brevitz.core.domain.home.HomeRepository
import dev.brevitz.core.domain.toMaybeError
import io.reactivex.Maybe

class HomeDataRepository(private val store: HomeStore, private val api: HomeApi) : HomeRepository {
    override fun getFigures(): ObservableRemoteData<List<Figure>> =
        store.get(HomeStoreKey)
            .syncIfEmpty(fetchFigures())

    private fun fetchFigures(): Maybe<RemoteError> =
        api.getFigures()
            .mapToRemoteData { list -> list?.map { it.toDomain() } }
            .doIfSuccess { store.store(it) }
            .flatMapMaybe { it.toMaybeError() }
}

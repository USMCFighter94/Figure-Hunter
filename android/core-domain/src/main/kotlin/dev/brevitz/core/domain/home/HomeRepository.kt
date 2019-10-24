package dev.brevitz.core.domain.home

import dev.brevitz.core.domain.ObservableRemoteData

interface HomeRepository {
    fun getFigures(): ObservableRemoteData<List<Figure>>
}

package dev.brevitz.figurehunter.core.domain.home

import dev.brevitz.figurehunter.core.domain.ObservableRemoteData

interface HomeRepository {
    fun getFigures(): ObservableRemoteData<List<Figure>>
}

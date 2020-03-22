package dev.brevitz.figurehunter.home.domain

import dev.brevitz.figurehunter.core.domain.ObservableRemoteData

interface HomeRepository {
    fun getFigures(): ObservableRemoteData<List<Figure>>
}

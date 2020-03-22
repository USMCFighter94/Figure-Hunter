package dev.brevitz.figurehunter.home.data

import dev.brevitz.figurehunter.core.domain.ReactiveStore
import dev.brevitz.figurehunter.home.domain.Figure

typealias HomeStore = ReactiveStore<HomeStoreKey, List<Figure>>

object HomeStoreKey

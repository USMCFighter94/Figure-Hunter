package dev.brevitz.figurehunter.core.data.home

import dev.brevitz.figurehunter.core.domain.ReactiveStore
import dev.brevitz.figurehunter.core.domain.home.Figure

typealias HomeStore = ReactiveStore<HomeStoreKey, List<Figure>>

object HomeStoreKey

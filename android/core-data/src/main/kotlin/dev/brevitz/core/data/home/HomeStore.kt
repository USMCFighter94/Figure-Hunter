package dev.brevitz.core.data.home

import dev.brevitz.core.domain.ReactiveStore
import dev.brevitz.core.domain.home.Figure

typealias HomeStore = ReactiveStore<HomeStoreKey, List<Figure>>

object HomeStoreKey

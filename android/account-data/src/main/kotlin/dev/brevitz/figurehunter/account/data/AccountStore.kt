package dev.brevitz.figurehunter.account.data

import dev.brevitz.figurehunter.account.domain.User
import dev.brevitz.figurehunter.core.domain.ReactiveStore

typealias AccountStore = ReactiveStore<Int, User>

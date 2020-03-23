package dev.brevitz.figurehunter.account.domain

import dev.brevitz.figurehunter.core.domain.ObservableRemoteData

interface AccountRepository {
    fun getUser(): ObservableRemoteData<User>
}

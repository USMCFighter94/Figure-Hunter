package dev.brevitz.figurehunter.account.data

import dev.brevitz.figurehunter.account.domain.AccountRepository
import dev.brevitz.figurehunter.account.domain.User
import dev.brevitz.figurehunter.core.domain.ObservableRemoteData
import dev.brevitz.figurehunter.core.domain.RemoteData
import io.reactivex.Observable
import javax.inject.Inject

class AccountDataRepository @Inject constructor() : AccountRepository {
    override fun getUser(): ObservableRemoteData<User> {
        return Observable.just(RemoteData.NotAsked)
    }
}

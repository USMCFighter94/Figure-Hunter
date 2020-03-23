package dev.brevitz.figurehunter.account.data

import dev.brevitz.figurehunter.account.data.api.AccountApi
import dev.brevitz.figurehunter.account.domain.AccountRepository
import dev.brevitz.figurehunter.account.domain.User
import dev.brevitz.figurehunter.core.data.doIfSuccess
import dev.brevitz.figurehunter.core.data.mapToRemoteData
import dev.brevitz.figurehunter.core.data.syncIfEmpty
import dev.brevitz.figurehunter.core.domain.ObservableRemoteData
import dev.brevitz.figurehunter.core.domain.RemoteData
import dev.brevitz.figurehunter.core.domain.RemoteError
import dev.brevitz.figurehunter.core.domain.toMaybeError
import io.reactivex.Maybe
import javax.inject.Inject

class AccountDataRepository @Inject constructor(
    private val store: AccountStore,
    private val api: AccountApi
) : AccountRepository {

    override fun getUser(): ObservableRemoteData<User> =
        store.get(1)
            .syncIfEmpty(fetchUser(1))
            .startWith(RemoteData.Loading)

    private fun fetchUser(id: Int): Maybe<RemoteError> =
        api.getUser(id)
            .mapToRemoteData { it?.toDomain() }
            .doIfSuccess { store.store(it) }
            .flatMapMaybe { it.toMaybeError() }
}

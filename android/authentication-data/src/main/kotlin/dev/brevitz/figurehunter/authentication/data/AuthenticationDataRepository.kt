package dev.brevitz.figurehunter.authentication.data

import dev.brevitz.figurehunter.authentication.domain.AuthenticationRepository
import dev.brevitz.figurehunter.authentication.domain.LoginData
import dev.brevitz.figurehunter.authentication.domain.RegisterData
import dev.brevitz.figurehunter.authentication.domain.Token
import dev.brevitz.figurehunter.core.data.mapToRemoteData
import dev.brevitz.figurehunter.core.domain.ObservableRemoteData
import dev.brevitz.figurehunter.core.domain.RemoteData
import javax.inject.Inject

class AuthenticationDataRepository @Inject constructor(private val api: AuthenticationApi) : AuthenticationRepository {
    override fun login(data: LoginData): ObservableRemoteData<Token> =
        api.login(data)
            .mapToRemoteData { it?.toDomain() }
            .toObservable()
            .startWith(RemoteData.Loading)

    override fun register(data: RegisterData): ObservableRemoteData<Token> =
        api.register(data)
            .mapToRemoteData { it?.toDomain() }
            .toObservable()
            .startWith(RemoteData.Loading)
}

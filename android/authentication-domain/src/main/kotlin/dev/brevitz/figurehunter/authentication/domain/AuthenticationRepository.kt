package dev.brevitz.figurehunter.authentication.domain

import dev.brevitz.figurehunter.core.domain.ObservableRemoteData

interface AuthenticationRepository {
    fun login(data: LoginData): ObservableRemoteData<Token>
    fun register(data: RegisterData): ObservableRemoteData<Token>
}

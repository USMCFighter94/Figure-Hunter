package dev.brevitz.figurehunter.authentication.data

import dagger.Binds
import dagger.Module
import dev.brevitz.figurehunter.authentication.domain.AuthenticationRepository
import dev.brevitz.figurehunter.core.data.FeatureScope

@Module
interface AuthenticationModule {
    @Binds
    @FeatureScope
    fun authenticationRepository(dataRepository: AuthenticationDataRepository): AuthenticationRepository
}

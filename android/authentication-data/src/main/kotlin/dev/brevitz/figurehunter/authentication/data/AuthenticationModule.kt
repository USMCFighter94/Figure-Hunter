package dev.brevitz.figurehunter.authentication.data

import dagger.Module
import dagger.Provides
import dev.brevitz.figurehunter.authentication.domain.AuthenticationRepository
import dev.brevitz.figurehunter.core.data.FeatureScope
import dev.brevitz.figurehunter.core.data.di.ServiceCreator

@Module
object AuthenticationModule {
    @Provides
    @FeatureScope
    fun authenticationApi(serviceCreator: ServiceCreator) = serviceCreator.create(AuthenticationApi::class.java)

    @Provides
    @FeatureScope
    fun authenticationRepository(api: AuthenticationApi): AuthenticationRepository = AuthenticationDataRepository(api)
}

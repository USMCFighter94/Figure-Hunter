package dev.brevitz.figurehunter.account.data

import dagger.Module
import dagger.Provides
import dev.brevitz.figurehunter.account.data.api.AccountApi
import dev.brevitz.figurehunter.core.data.FeatureScope
import dev.brevitz.figurehunter.core.data.MemoryReactiveStore
import dev.brevitz.figurehunter.core.data.di.ServiceCreator

@Module
object AccountModule {
    @Provides
    @FeatureScope
    fun accountStore(): AccountStore = MemoryReactiveStore { it.id }

    @Provides
    @FeatureScope
    fun accountApi(serviceCreator: ServiceCreator): AccountApi = serviceCreator.create(AccountApi::class.java)
}

package dev.brevitz.figurehunter.account.data

import dagger.Binds
import dagger.Module
import dev.brevitz.figurehunter.account.domain.AccountRepository
import dev.brevitz.figurehunter.core.data.FeatureScope

@Module
interface AccountRepositoryModule {
    @Binds
    @FeatureScope
    fun repository(dataRepository: AccountDataRepository): AccountRepository
}

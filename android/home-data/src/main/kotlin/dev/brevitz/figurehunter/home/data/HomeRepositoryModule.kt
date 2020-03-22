package dev.brevitz.figurehunter.home.data

import dagger.Binds
import dagger.Module
import dev.brevitz.figurehunter.core.data.FeatureScope
import dev.brevitz.figurehunter.home.domain.HomeRepository

@Module
interface HomeRepositoryModule {
    @Binds
    @FeatureScope
    fun homeRepository(dataRepository: HomeDataRepository): HomeRepository
}

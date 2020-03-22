package dev.brevitz.figurehunter.home.data

import dagger.Module
import dagger.Provides
import dev.brevitz.figurehunter.core.data.FeatureScope
import dev.brevitz.figurehunter.core.data.MemoryReactiveStore
import dev.brevitz.figurehunter.core.data.di.ServiceCreator
import dev.brevitz.figurehunter.home.domain.HomeRepository

@Module
object HomeModule {
    @Provides
    @FeatureScope
    fun homeApi(serviceCreator: ServiceCreator): HomeApi = serviceCreator.create(HomeApi::class.java)

    @Provides
    @FeatureScope
    fun homeStore(): HomeStore = MemoryReactiveStore { HomeStoreKey }

    @Provides
    @FeatureScope
    fun homeRepository(store: HomeStore, api: HomeApi): HomeRepository = HomeDataRepository(store, api)
}

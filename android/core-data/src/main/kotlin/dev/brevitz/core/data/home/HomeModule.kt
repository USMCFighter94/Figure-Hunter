package dev.brevitz.core.data.home

import dagger.Module
import dagger.Provides
import dev.brevitz.core.data.FeatureScope
import dev.brevitz.core.data.MemoryReactiveStore
import dev.brevitz.core.data.di.ServiceCreator
import dev.brevitz.core.domain.home.HomeRepository

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

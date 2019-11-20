package dev.brevitz.figurehunter.core.data.network

import dagger.Module
import dagger.Provides
import dev.brevitz.figurehunter.core.domain.Storage
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
object NetworkClientModule {
    @Provides
    @Singleton
    fun headerInterceptor(storage: Storage) = HeaderInterceptor(storage)

    @Provides
    @Singleton
    fun okHttp(headerInterceptor: HeaderInterceptor): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
        .addInterceptor(headerInterceptor)
        .build()
}

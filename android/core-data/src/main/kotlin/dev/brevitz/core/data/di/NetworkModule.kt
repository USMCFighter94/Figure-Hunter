package dev.brevitz.core.data.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule {
    @Provides
    @Singleton
    fun moshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun converter(moshi: Moshi): Converter.Factory = MoshiConverterFactory.create(moshi)

    @Provides
    @Singleton
    fun callAdapter(): CallAdapter.Factory = RxJava2CallAdapterFactory.create()

    @Provides
    @BaseUrl
    fun baseUrl(): String = "https://figure-hunter.herokuapp.com/"

    @Provides
    @Singleton
    fun retrofit(
        @BaseUrl baseUrl: String,
        converterFactory: Converter.Factory,
        callAdapterFactory: CallAdapter.Factory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(converterFactory)
        .addCallAdapterFactory(callAdapterFactory)
        .build()

    @Provides
    @Singleton
    fun provideServiceCreator(retrofit: Retrofit): ServiceCreator = object : ServiceCreator {
        override fun <A> create(service: Class<A>): A = retrofit.create(service)
    }
}

package dev.brevitz.figurehunter.core.data.network

import dev.brevitz.figurehunter.core.domain.Option
import dev.brevitz.figurehunter.core.domain.Storage
import dev.brevitz.figurehunter.core.domain.StorageKey
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(storage: Storage) : Interceptor {
    private val disposables = CompositeDisposable()
    private var token: String? = null

    init {
        storage.observeString(StorageKey.TOKEN)
            .subscribeOn(Schedulers.io())
            .subscribe {
                token = when (it) {
                    is Option.Some -> it.value
                    else -> null
                }
            }
            .addTo(disposables)
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        if (token != null) {
            request = request.newBuilder()
                .header(TOKEN_KEY, String.format(TOKEN_VALUE, token))
                .build()
        }

        return chain.proceed(request)
    }

    private companion object {
        private const val TOKEN_KEY = "Authorization"
        private const val TOKEN_VALUE = "Bearer %s"
    }
}

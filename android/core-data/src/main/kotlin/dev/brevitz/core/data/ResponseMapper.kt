package dev.brevitz.core.data

import dev.brevitz.core.domain.RemoteData
import dev.brevitz.core.domain.RemoteError
import io.reactivex.Single
import retrofit2.Response

inline fun <A : Any, B : Any> Single<Response<A>>.mapToRemoteData(crossinline converter: (A?) -> B?): Single<RemoteData<B, RemoteError>> =
    map {
        if (it.isSuccessful) {
            val successValue = converter(it.body())

            if (successValue != null) {
                RemoteData.succeed(successValue)
            } else {
                RemoteData.error(RemoteError.SyncError)
            }
        } else {
            RemoteData.error(RemoteError.HttpError(it.code(), it.message()))
        }
    }

inline fun <A : Any> Single<RemoteData<A, RemoteError>>.doIfSuccess(crossinline onSuccess: (A) -> Unit): Single<RemoteData<A, RemoteError>> =
    doOnSuccess {
        if (it.isSuccess()) {
            onSuccess((it as RemoteData.Success).data)
        }
    }

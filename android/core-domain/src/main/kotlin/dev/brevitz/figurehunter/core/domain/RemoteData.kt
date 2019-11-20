package dev.brevitz.figurehunter.core.domain

import io.reactivex.Maybe
import io.reactivex.Observable

sealed class RemoteData<out T : Any, out U : Any> {
    object NotAsked : RemoteData<Nothing, Nothing>()

    object Loading : RemoteData<Nothing, Nothing>()

    data class Success<T : Any>(val data: T) : RemoteData<T, Nothing>()

    data class Error<out U : Any>(val error: U) : RemoteData<Nothing, U>()

    fun isNotAsked() = this is NotAsked
    fun isLoading() = this is Loading
    fun isSuccess() = this is Success
    fun isError() = this is Error

    companion object {
        fun <A : Any> succeed(data: A): RemoteData<A, Nothing> = Success(data)

        fun <E : Any> error(error: E): RemoteData<Nothing, E> = Error(error)
    }
}

fun <A : Any, E : Any> RemoteData<A, E>.toMaybeError(): Maybe<E> = when (this) {
    is RemoteData.Error -> Maybe.just(error)
    else -> Maybe.empty()
}

inline fun <A : Any, B : Any, E : Any> RemoteData<A, E>.mapSuccess(mapper: (A) -> B): RemoteData<B, E> = when (this) {
    is RemoteData.Success -> RemoteData.succeed(mapper(data))
    is RemoteData.Error -> RemoteData.error(error)
    is RemoteData.NotAsked -> this
    is RemoteData.Loading -> this
}

inline fun <A : Any, E : Any> Observable<RemoteData<A, E>>.doIfSuccess(crossinline f: (A) -> Unit): Observable<RemoteData<A, E>> =
    doOnNext {
        when (it) {
            is RemoteData.Success -> f(it.data)
        }
    }

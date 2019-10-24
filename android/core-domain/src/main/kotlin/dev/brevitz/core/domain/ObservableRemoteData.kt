package dev.brevitz.core.domain

import io.reactivex.Observable

typealias ObservableRemoteData<A> = Observable<RemoteData<A, RemoteError>>

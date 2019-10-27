package dev.brevitz.figurehunter.core.domain

import io.reactivex.Observable

typealias ObservableRemoteData<A> = Observable<RemoteData<A, RemoteError>>

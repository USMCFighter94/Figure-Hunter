package dev.brevitz.figurehunter.core.domain

import io.reactivex.Observable

interface Storage {
    fun observeString(key: StorageKey): Observable<Option<String>>
    fun setString(key: StorageKey, value: String?)
}

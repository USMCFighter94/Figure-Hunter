package dev.brevitz.figurehunter.core.data

import android.content.Context
import androidx.core.content.edit
import dev.brevitz.figurehunter.core.domain.Option
import dev.brevitz.figurehunter.core.domain.Storage
import dev.brevitz.figurehunter.core.domain.StorageKey
import dev.brevitz.figurehunter.core.domain.toOption
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

class SharedPreferences(context: Context) : Storage {
    private val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    private val stringSubjects = mutableMapOf<StorageKey, Subject<Option<String>>>()

    override fun observeString(key: StorageKey): Observable<Option<String>> = Observable.defer {
        getOrCreateStringPublisher(key)
            .startWith(prefs.getString(key.name, null).toOption())
            .hide()
    }

    override fun setString(key: StorageKey, value: String?) {
        prefs.edit { putString(key.name, value) }
        getOrCreateStringPublisher(key).onNext(value.toOption())
    }

    private fun getOrCreateStringPublisher(key: StorageKey): Subject<Option<String>> =
        stringSubjects.getOrPut(key) { PublishSubject.create<Option<String>>() }

    private companion object {
        private const val PREF_NAME = "FigureHunter"
    }
}

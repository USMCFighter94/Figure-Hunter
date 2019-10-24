package dev.brevitz.core.data

import dev.brevitz.core.domain.Option
import dev.brevitz.core.domain.ReactiveStore
import dev.brevitz.core.domain.toOption
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

class MemoryReactiveStore<K : Any, V : Any>(
    private val cache: ConcurrentMap<K, V> = ConcurrentHashMap(),
    private inline val keyForItem: (V) -> K
) : ReactiveStore<K, V> {

    private val itemsSubject = PublishSubject.create<Option<Set<V>>>().toSerialized()
    private val itemRelays = ConcurrentHashMap<K, Subject<Option<V>>>()

    override fun store(item: V) {
        val key = keyForItem(item)
        cache[key] = item

        getOrCreatePublisher(key).onNext(item.toOption())
        itemsSubject.onNext(getAllItems())
    }


    override fun get(key: K): Observable<Option<V>> =
        Observable.defer {
            val item = cache[key].toOption()
            getOrCreatePublisher(key).startWith(item)
        }

    override fun getAll(): Observable<Option<Set<V>>> =
        Observable.defer {
            val allItems = getAllItems()
            itemsSubject.startWith(allItems)
        }

    override fun clear() {
        cache.clear()
        itemsSubject.onNext(getAllItems())
        updateExistingPublishers()
    }

    override fun clear(key: K) {
        cache.remove(key)
        itemsSubject.onNext(getAllItems())
        updateExistingPublishers()
    }

    private fun getAllItems(): Option<Set<V>> = cache.values.toSet().toOption()

    @Synchronized
    private fun getOrCreatePublisher(key: K): Subject<Option<V>> =
        itemRelays.getOrPut(key) {
            PublishSubject.create<Option<V>>().toSerialized()
        }

    @Synchronized
    private fun updateExistingPublishers() {
        val keys = itemRelays.keys().toList().toSet()
        keys.forEach {
            val item = cache[it].toOption()
            updateItemPublisher(it, item)
        }
    }

    private fun updateItemPublisher(key: K, item: Option<V>) {
        when (val publisher = itemRelays[key].toOption()) {
            is Option.Some -> publisher.value.onNext(item)
            is Option.None -> {
            }
        }
    }
}
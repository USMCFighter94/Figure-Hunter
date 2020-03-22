package dev.brevitz.figurehunter.core.data.di

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ComponentManager @Inject constructor() {
    private var components: Map<String, DaggerComponent> = emptyMap()

    inline fun <A : DaggerComponent> getOrCreate(key: String, initializer: () -> A): A {
        return getComponent(key) ?: initializer().also { setComponent(key, it) }
    }

    fun setComponent(key: String, component: DaggerComponent) {
        components = components.plus(key to component)
    }

    fun <A : DaggerComponent> getComponent(key: String): A? = components[key] as? A

    fun destroyComponent(key: String) {
        var component = components[key]
        if (component != null) {
            components = components.minus(key)
            component = null
        }
    }
}

package dev.brevitz.figurehunter.core.data.di

interface ServiceCreator {
    fun <A> create(service: Class<A>): A
}

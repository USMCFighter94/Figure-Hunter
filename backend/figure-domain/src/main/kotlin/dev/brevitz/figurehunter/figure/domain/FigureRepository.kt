package dev.brevitz.figurehunter.figure.domain

interface FigureRepository {
    fun get(id: String): Figure?
    fun getAll(): List<Figure>
}

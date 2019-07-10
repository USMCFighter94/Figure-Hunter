package backend.repository

import backend.Figure

object FigureRepository : Repository<Figure>() {
    private val figures = mutableSetOf(Figure(1, "Finn (Jakku)", 1, "The Black Series - The Force Awakens", 2015))

    override fun add(newItem: Figure): Figure {
        figures.add(newItem)
        return newItem
    }

    override fun get(id: String): Figure = figures.find { it.id == id.toInt() } ?: Figure(0, "", 0, "", 1969)

    override fun getAll() = figures.toList()

    override fun remove(id: String) {
        figures.removeAll { it.id == id.toInt() }
    }
}
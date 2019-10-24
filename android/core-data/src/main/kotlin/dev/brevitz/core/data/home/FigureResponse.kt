package dev.brevitz.core.data.home

import dev.brevitz.core.domain.home.Figure

class FigureResponse(
    val id: Int?,
    val name: String?,
    val num: Int?,
    val series: String?,
    val year: Int?
) {
    fun toDomain() = Figure(id!!, name!!, num!!, series!!, year!!)
}

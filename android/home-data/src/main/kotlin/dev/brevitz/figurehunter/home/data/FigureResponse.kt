package dev.brevitz.figurehunter.home.data

import dev.brevitz.figurehunter.home.domain.Figure

data class FigureResponse(
    val id: Int?,
    val name: String?,
    val num: Int?,
    val series: String?,
    val year: Int?
) {
    fun toDomain() = Figure(id!!, name!!, num!!, series!!, year!!)
}

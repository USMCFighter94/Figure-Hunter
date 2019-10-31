package dev.brevitz.figurehunter.figure.data

import dev.brevitz.figurehunter.figure.domain.Figure
import io.kotlintest.properties.Gen

class FigureGenerator : Gen<Figure> {
    override fun constants() = emptyList<Figure>()

    override fun random() = generateSequence {
        Figure(
            Gen.int().random().first(),
            Gen.string().random().first(),
            Gen.int().random().first(),
            Gen.string().random().first(),
            Gen.int().random().first()
        )
    }
}

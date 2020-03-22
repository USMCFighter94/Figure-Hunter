package dev.brevitz.figurehunter.home.ui

import com.airbnb.epoxy.TypedEpoxyController
import dev.brevitz.figurehunter.home.domain.Figure

class HomeController : TypedEpoxyController<List<Figure>>() {
    override fun buildModels(data: List<Figure>) {
        data.forEach {
            HomeModel(it)
                .id(it.id)
                .addTo(this)
        }
    }
}

package dev.brevitz.figurehunter.home

import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.EpoxyModelWithView
import dev.brevitz.figurehunter.R
import dev.brevitz.figurehunter.core.domain.home.Figure
import dev.brevitz.figurehunter.core.ui.inflateAs

data class HomeModel(private val figure: Figure) : EpoxyModelWithView<ConstraintLayout>() {

    override fun bind(view: ConstraintLayout) {
        super.bind(view)
        with(view) {
            findViewById<TextView>(R.id.homeItemName).text = figure.name
            findViewById<TextView>(R.id.homeItemNumber).text = figure.num.toString()
            findViewById<TextView>(R.id.homeItemSeries).text = figure.series
            findViewById<TextView>(R.id.homeItemYear).text = figure.year.toString()
        }
    }

    override fun buildView(parent: ViewGroup): ConstraintLayout = parent.inflateAs(R.layout.item_home)
}

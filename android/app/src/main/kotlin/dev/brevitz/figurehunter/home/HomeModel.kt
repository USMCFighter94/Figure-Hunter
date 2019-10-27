package dev.brevitz.figurehunter.home

import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.EpoxyModelWithView
import dev.brevitz.figurehunter.core.domain.home.Figure
import dev.brevitz.figurehunter.R
import dev.brevitz.figurehunter.core.ui.inflateAs
import kotlinx.android.synthetic.main.item_home.view.*

data class HomeModel(private val figure: Figure) : EpoxyModelWithView<ConstraintLayout>() {

    override fun bind(view: ConstraintLayout) {
        super.bind(view)
        with(view) {
            homeItemName.text = figure.name
            homeItemNumber.text = figure.num.toString()
            homeItemSeries.text = figure.series
            homeItemYear.text = figure.year.toString()
        }
    }

    override fun buildView(parent: ViewGroup): ConstraintLayout = parent.inflateAs(R.layout.item_home)
}

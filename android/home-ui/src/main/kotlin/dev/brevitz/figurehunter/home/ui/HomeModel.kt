package dev.brevitz.figurehunter.home.ui

import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.EpoxyModelWithView
import dev.brevitz.figurehunter.core.ui.inflateAs
import dev.brevitz.figurehunter.home.domain.Figure
import dev.brevitz.figurehunter.home.ui.databinding.ItemHomeBinding

data class HomeModel(private val figure: Figure) : EpoxyModelWithView<ConstraintLayout>() {

    override fun bind(view: ConstraintLayout) {
        super.bind(view)
        with(ItemHomeBinding.bind(view)) {
            homeItemName.text = figure.name
            homeItemNumber.text = figure.num.toString()
            homeItemSeries.text = figure.series
            homeItemYear.text = figure.year.toString()
        }
    }

    override fun buildView(parent: ViewGroup): ConstraintLayout = parent.inflateAs(R.layout.item_home)
}

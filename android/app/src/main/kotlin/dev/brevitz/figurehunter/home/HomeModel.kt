package dev.brevitz.figurehunter.home

import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.EpoxyModelWithView
import dev.brevitz.figurehunter.R
import dev.brevitz.figurehunter.core.domain.home.Figure
import dev.brevitz.figurehunter.core.ui.inflateAs
import dev.brevitz.figurehunter.databinding.ItemHomeBinding

data class HomeModel(private val figure: Figure) : EpoxyModelWithView<ConstraintLayout>() {

    private var binding: ItemHomeBinding? = null

    override fun bind(view: ConstraintLayout) {
        super.bind(view)
        binding = ItemHomeBinding.bind(view)

        binding?.homeItemName?.text = figure.name
        binding?.homeItemNumber?.text = figure.num.toString()
        binding?.homeItemSeries?.text = figure.series
        binding?.homeItemYear?.text = figure.year.toString()
    }

    override fun unbind(view: ConstraintLayout) {
        super.unbind(view)
        binding = null
    }

    override fun buildView(parent: ViewGroup): ConstraintLayout = parent.inflateAs(R.layout.item_home)
}

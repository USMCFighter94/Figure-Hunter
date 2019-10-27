package dev.brevitz.figurehunter.authentication.ui

import android.view.ViewGroup
import android.widget.LinearLayout
import com.airbnb.epoxy.EpoxyModelWithView
import dev.brevitz.figurehunter.core.ui.inflateAs
import kotlinx.android.synthetic.main.view_register.view.*

data class RegisterView(private val goToLogin: () -> Unit) : EpoxyModelWithView<LinearLayout>() {
    override fun bind(view: LinearLayout) {
        super.bind(view)
        with(view) {
            alreadyHaveAccount.setOnClickListener { goToLogin() }
        }
    }

    override fun buildView(parent: ViewGroup): LinearLayout = parent.inflateAs(R.layout.view_register)

    companion object {
        const val ID = "RegisterView"
    }
}

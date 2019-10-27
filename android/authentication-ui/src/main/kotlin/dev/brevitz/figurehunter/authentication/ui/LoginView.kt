package dev.brevitz.figurehunter.authentication.ui

import android.view.ViewGroup
import android.widget.LinearLayout
import com.airbnb.epoxy.EpoxyModelWithView
import dev.brevitz.figurehunter.core.ui.inflateAs
import kotlinx.android.synthetic.main.view_login.view.*

data class LoginView(private val goToRegister: () -> Unit) : EpoxyModelWithView<LinearLayout>() {
    override fun bind(view: LinearLayout) {
        super.bind(view)
        with(view) {
            newHere.setOnClickListener { goToRegister() }
        }
    }

    override fun buildView(parent: ViewGroup): LinearLayout = parent.inflateAs(R.layout.view_login)

    companion object {
        const val ID = "LoginView"
    }
}

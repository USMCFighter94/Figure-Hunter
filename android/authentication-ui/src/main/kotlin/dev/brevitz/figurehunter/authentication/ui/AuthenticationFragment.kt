package dev.brevitz.figurehunter.authentication.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.fragment_authentication.*

class AuthenticationFragment : Fragment(R.layout.fragment_authentication), AuthenticationClickListener {
    private val controller = AuthenticationController(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authenticationViewPager.adapter = controller.adapter
        authenticationViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        controller.requestModelBuild()
    }

    override fun goToLogin() {
        authenticationViewPager.currentItem = 0
    }

    override fun goToRegister() {
        authenticationViewPager.currentItem = 1
    }

    override fun finished() {
        findNavController().popBackStack()
    }
}

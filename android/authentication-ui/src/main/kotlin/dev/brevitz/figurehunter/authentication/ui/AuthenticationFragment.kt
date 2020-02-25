package dev.brevitz.figurehunter.authentication.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import dev.brevitz.figurehunter.authentication.ui.databinding.FragmentAuthenticationBinding

class AuthenticationFragment : Fragment(R.layout.fragment_authentication), AuthenticationClickListener {
    private val controller = AuthenticationController(this)

    private var binding: FragmentAuthenticationBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAuthenticationBinding.bind(view)

        binding?.authenticationViewPager?.adapter = controller.adapter
        binding?.authenticationViewPager?.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        controller.requestModelBuild()
    }

    override fun goToLogin() {
        binding?.authenticationViewPager?.currentItem = 0
    }

    override fun goToRegister() {
        binding?.authenticationViewPager?.currentItem = 1
    }

    override fun finished() {
        findNavController().popBackStack()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}

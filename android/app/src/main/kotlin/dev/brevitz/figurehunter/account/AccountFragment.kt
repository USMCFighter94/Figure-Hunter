package dev.brevitz.figurehunter.account

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dev.brevitz.figurehunter.R
import dev.brevitz.figurehunter.core.data.di.provideCoreComponent
import dev.brevitz.figurehunter.databinding.FragmentAccountBinding
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class AccountFragment : Fragment(R.layout.fragment_account) {
    @Inject
    lateinit var viewModel: AccountViewModel

    private var binding: FragmentAccountBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.provideCoreComponent()?.let {
            it.componentManager().getOrCreate(AccountComponent.KEY) {
                DaggerAccountComponent.builder()
                    .coreComponent(it)
                    .build()
            }.inject(this)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAccountBinding.bind(view)
    }

    override fun onResume() {
        super.onResume()
        viewModel.observe()
            .map { it.loggedIn }
            .distinctUntilChanged()
            .subscribe { loggedIn ->
                if (!loggedIn) {
                    findNavController().navigate(R.id.authenticateUser)
                }
            }
            .addTo(viewModel.disposables)

        viewModel.start()
    }

    override fun onPause() {
        super.onPause()
        viewModel.stop()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}

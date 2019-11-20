package dev.brevitz.figurehunter.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dev.brevitz.figurehunter.R
import dev.brevitz.figurehunter.core.data.di.provideCoreComponent
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class AccountFragment : Fragment(R.layout.fragment_account) {
    @Inject
    lateinit var viewModel: AccountViewModel

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
}

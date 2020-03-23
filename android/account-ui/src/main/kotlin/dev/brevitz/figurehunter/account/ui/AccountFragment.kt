package dev.brevitz.figurehunter.account.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import dev.brevitz.figurehunter.account.ui.databinding.FragmentAccountBinding
import dev.brevitz.figurehunter.core.data.di.provideCoreComponent
import dev.brevitz.figurehunter.core.domain.RemoteData
import dev.brevitz.figurehunter.core.ui.deepLink
import dev.brevitz.figurehunter.core.ui.load
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
            .map { it.userData }
            .distinctUntilChanged()
            .subscribe { remoteData ->
                when (remoteData) {
                    is RemoteData.Success -> {
                        val user = remoteData.data

                        user.avatar?.let {
                            binding?.userImage?.load(it)
                        }

                        binding?.firstName?.text = user.firstName
                        binding?.lastName?.text = user.lastName
                    }
                }
            }.addTo(viewModel.disposables)

        viewModel.observe()
            .map { it.loggedIn }
            .distinctUntilChanged()
            .subscribe { loggedIn ->
                if (!loggedIn) {
                    activity?.deepLink(AUTHENTICATION_LINK)
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

    private companion object {
        private const val AUTHENTICATION_LINK = "https://figurehunter.com/login"
    }
}

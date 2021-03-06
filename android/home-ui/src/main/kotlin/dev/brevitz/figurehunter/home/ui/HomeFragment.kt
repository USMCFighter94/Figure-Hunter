package dev.brevitz.figurehunter.home.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dev.brevitz.figurehunter.core.data.di.provideCoreComponent
import dev.brevitz.figurehunter.core.domain.RemoteData
import dev.brevitz.figurehunter.home.ui.databinding.FragmentHomeBinding
import io.reactivex.rxkotlin.addTo
import timber.log.Timber
import javax.inject.Inject

class HomeFragment : Fragment(R.layout.fragment_home) {
    @Inject
    lateinit var viewModel: HomeViewModel

    private val controller = HomeController()

    private var binding: FragmentHomeBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.provideCoreComponent()?.let {
            it.componentManager().getOrCreate(HomeComponent.KEY) {
                DaggerHomeComponent.builder()
                    .coreComponent(it)
                    .build()
            }.inject(this)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        binding?.homeRecyclerView?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = controller.adapter
            isNestedScrollingEnabled = true
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }

        viewModel.start()
    }

    override fun onResume() {
        super.onResume()
        viewModel.observe()
            .distinctUntilChanged()
            .doOnNext { binding?.homeLoadingIndicator?.isVisible = it.isLoading() }
            .subscribe {
                when (it) {
                    is RemoteData.Success -> controller.setData(it.data)
                    is RemoteData.Error -> Timber.e("Error: %s", it.error)
                }
            }.addTo(viewModel.disposables)
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

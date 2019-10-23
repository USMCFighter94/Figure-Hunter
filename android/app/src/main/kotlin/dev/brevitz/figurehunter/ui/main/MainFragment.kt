package dev.brevitz.figurehunter.ui.main

import androidx.fragment.app.Fragment
import dev.brevitz.figurehunter.R

class MainFragment private constructor() : Fragment(R.layout.main_fragment) {
    private val viewModel = MainViewModel()

    companion object {
        fun newInstance() = MainFragment()
    }
}

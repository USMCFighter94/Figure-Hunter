package dev.brevitz.figurehunter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        NavigationUI.setupWithNavController(bottomNavView, findNavController(R.id.navHostFragment))
    }

    override fun onSupportNavigateUp() = findNavController(R.id.navHostFragment).navigateUp()
}

package com.example.footballfixtures

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.updatePadding
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.footballfixtures.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var appBarConfig: AppBarConfiguration
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
        windowInsetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        ViewCompat.setOnApplyWindowInsetsListener(window.decorView) { view, windowInsets ->
            windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
            ViewCompat.onApplyWindowInsets(view, windowInsets)
        }

        setContentView(binding.root)

        setUpNavComponents()

    }

    private fun setUpNavComponents() {
        binding.apply {
            setSupportActionBar(topAppBar)

            val host = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
                ?: return
            navController = host.navController

            appBarConfig = AppBarConfiguration(
                setOf(R.id.fixturesFragment,R.id.competitionsFragment),)

            setupActionBarWithNavController(
                navController = navController,
                configuration = appBarConfig
            )

            bottomNav.setupWithNavController(navController).also {
                ViewCompat.setOnApplyWindowInsetsListener(bottomNav) { _, insets ->
                    val systemBars = insets.getInsets(WindowInsetsCompat.Type.navigationBars())
                    bottomNav.updatePadding(bottom = systemBars.bottom)
                    insets
                }
            }

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfig) || super.onSupportNavigateUp()
    }
}
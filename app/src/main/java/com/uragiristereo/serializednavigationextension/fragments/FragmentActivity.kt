package com.uragiristereo.serializednavigationextension.fragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.uragiristereo.serializednavigationextension.R
import com.uragiristereo.serializednavigationextension.SneRoute
import com.uragiristereo.serializednavigationextension.databinding.ActivityFragmentBinding
import com.uragiristereo.serializednavigationextension.navigation.fragment.fragment
import com.uragiristereo.serializednavigationextension.runtime.createGraph
import com.uragiristereo.serializednavigationextension.runtime.toBundle

class FragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(ActivityFragmentBinding.inflate(layoutInflater).root)
        setupNavGraph()
    }

    private fun setupNavGraph() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        val graph = navController.createGraph(
            startDestination = SneRoute.Home::class,
        ) {
            fragment<HomeFragment, SneRoute.Home>()
            fragment<NotificationFragment, SneRoute.Notification>()
        }

        navController.setGraph(
            graph = graph,
            startDestinationArgs = SneRoute.Home.toBundle(),
        )
    }
}

package com.example.vinilos

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.vinilos.databinding.ActivityHomeBinding
import com.google.android.material.navigation.NavigationView

class HomeActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        val currentSection = intent.getStringExtra("section")

        setSupportActionBar(binding.appBarHome.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout

        val navView: NavigationView = binding.navView
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController: NavController = navHostFragment.navController

        val navGraph = navController.navInflater.inflate(R.navigation.mobile_navigation)
        navGraph.startDestination = if (currentSection == "guest") R.id.nav_guest else R.id.nav_collector_landing
        navController.graph = navGraph

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_guest, R.id.nav_collector_landing, R.id.nav_artists, R.id.nav_albums, R.id.nav_collectors
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        if (currentSection == "collector") {
            navView.menu.findItem(R.id.nav_artists).isVisible = false
            navView.menu.findItem(R.id.nav_collectors).isVisible = false
        }

        val logoutButton: TextView = findViewById(R.id.logout_button)
        logoutButton.setOnClickListener {
            val backIntent = Intent(this, EntranceActivity::class.java)
            startActivity(backIntent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
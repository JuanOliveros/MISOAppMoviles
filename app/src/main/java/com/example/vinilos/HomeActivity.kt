package com.example.vinilos

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.vinilos.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = getIntent()
        val currentSection = intent.getStringExtra("section")

        setSupportActionBar(binding.appBarHome.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout

        //Necesario para colocar a la izquierda el menú
        //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_home) // AQUI

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
            val intent = Intent(this, EntranceActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_home)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
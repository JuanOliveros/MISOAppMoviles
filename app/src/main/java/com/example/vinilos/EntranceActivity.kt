package com.example.vinilos

import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import com.example.vinilos.databinding.ActivityEntranceBinding

class EntranceActivity : AppCompatActivity() {

    private val collectorSection = "collector"
    private val guestSection = "guest"

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityEntranceBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun onCollectorButtonClick(view: android.view.View) {
        val intent = Intent(this, HomeActivity::class.java).apply {
            putExtra("section", collectorSection)
        }
        startActivity(intent)
    }

    fun onGuestButtonClick(view: android.view.View) {
        val intent = Intent(this, HomeActivity::class.java).apply {
            putExtra("section", guestSection)
        }
        startActivity(intent)
    }
}
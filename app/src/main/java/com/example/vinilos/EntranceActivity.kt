package com.example.vinilos

import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.example.vinilos.databinding.ActivityEntranceBinding

class EntranceActivity : AppCompatActivity() {

    private val collectorSection = "collector"
    private val guestSection = "guest"

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityEntranceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val guestButton: Button = findViewById(R.id.guest_button)
        val collectorButton: Button = findViewById(R.id.collector_button)

        guestButton.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java).apply {
                putExtra("section", guestSection)
            }
            startActivity(intent)
        }

        collectorButton.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java).apply {
                putExtra("section", collectorSection)
            }
            startActivity(intent)
        }
    }
}
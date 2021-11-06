package com.example.vinilos

import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.example.vinilos.databinding.ActivityEntranceBinding

class EntranceActivity : AppCompatActivity() {

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityEntranceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val guestButton: Button = findViewById(R.id.guest_button)
        val collectorButton: Button = findViewById(R.id.collector_button)

        guestButton.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("section", "guest")
            startActivity(intent)
        }

        collectorButton.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("section", "collector")
            startActivity(intent)
        }
    }
}
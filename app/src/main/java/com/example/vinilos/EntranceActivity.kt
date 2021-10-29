package com.example.vinilos

import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint
import android.os.Bundle
import com.example.vinilos.databinding.ActivityEntranceBinding

class EntranceActivity : AppCompatActivity() {

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityEntranceBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
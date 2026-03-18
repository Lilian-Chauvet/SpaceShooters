package com.example.spaceshooters

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.TextView

class CreditsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_credits)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        startCreditsScroll();
    }




    private fun startCreditsScroll() {
        val credits = findViewById<TextView>(R.id.tv_credits)

        credits.post {
            val screenHeight = (credits.parent as View).height.toFloat()
            val textHeight = credits.height.toFloat()

            // Position de départ (en bas de l'écran)
            credits.translationY = screenHeight

            credits.animate()
                .translationY(-textHeight)
                .setDuration(20000L) // durée (20 secondes)
                .setInterpolator(LinearInterpolator())
                .start()
        }
    }
}
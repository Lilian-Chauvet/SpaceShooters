package com.example.spaceshooters

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class GameActivity : AppCompatActivity() {
    lateinit var mySensorManager: MySensorManager
    lateinit var gameArea: ConstraintLayout
    lateinit var blaster: Blaster

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_game)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        mySensorManager = MySensorManager(this)

        mySensorManager.displaySensors()

        gameArea = findViewById(R.id.gameArea)

        startGame()
    }


    private fun startGame() {
        blaster = Blaster(this, 200, 200)
    }
}
package com.example.spaceshooters

import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class GameActivity : AppCompatActivity(){
    lateinit var mySensorManager: MySensorManager
    lateinit var gameArea: ConstraintLayout
    lateinit var blaster: Blaster

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_game)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.gameArea)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        mySensorManager = MySensorManager(this)
        gameArea = findViewById(R.id.gameArea)

        startGame()
    }

    override fun onResume() {
        mySensorManager.start()
        super.onResume()
    }

    override fun onPause() {
        mySensorManager.stop()
        super.onPause()
    }

    private fun startGame() {
        blaster = Blaster(this)
    }
}
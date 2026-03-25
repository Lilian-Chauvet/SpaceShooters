package com.example.spaceshooters

import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.lang.Thread.sleep

class GameActivity : AppCompatActivity(){
    lateinit var mySensorManager: MySensorManager
    lateinit var gameArea: ConstraintLayout
    lateinit var blaster: Blaster
    val enemies = mutableListOf<Enemy>()

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
        gameArea.setOnClickListener{
            blaster.shoot()
        }
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

        spawnEnemies()
    }

    private fun spawnEnemies() {
        for (i in 0 until 100) {
            gameArea.postDelayed({
                val enemy = Enemy(this)
                enemies.add(enemy)
                enemy.attack()
            }, i * 2000L)
        }
    }
}
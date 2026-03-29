package com.example.spaceshooters

import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.lang.Thread.sleep

class GameActivity : AppCompatActivity() {
    lateinit var mySensorManager: MySensorManager
    lateinit var gameArea: ConstraintLayout
    lateinit var blaster: Blaster
    lateinit var tv_score: TextView
    lateinit var pauseMenu: View
    lateinit var iv_pauseButton: View
    var isPaused = false
    var score = 0

    val enemies = mutableListOf<Enemy>()
    val projectiles = mutableListOf<Projectile>()


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
        tv_score = findViewById(R.id.tv_score)
        pauseMenu = findViewById(R.id.pauseMenu)
        iv_pauseButton = findViewById(R.id.ib_pauseButton)
        iv_pauseButton.setOnClickListener {
            togglePause()
        }
        gameArea.setOnClickListener {
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
        gameLoop()
    }

    private fun spawnEnemy() {
        val enemy = Enemy(this)
        enemies.add(enemy)
        enemy.attack()
    }

    fun changeScore(value: Int = 1) {
        score += value
        tv_score.text = score.toString()
    }

    private fun togglePause() {
        isPaused = !isPaused

        if (isPaused) {
            pauseMenu.visibility = View.VISIBLE
        } else {
            pauseMenu.visibility = View.GONE
        }
    }

    fun gameLoop() {
        if (!isPaused) {
            // faire apparaitre un ennemi
            if (Math.random() < 0.01) {
                spawnEnemy()
            }

            // déplacer projectiles
            for (p in projectiles) {
                p.update()
            }

            // déplacer ennemis
            for (e in enemies) {
                e.update()
            }

            // collisions
            checkCollisions()
        }

        gameArea.postDelayed({ gameLoop() }, 16)
    }

    private fun checkCollisions() {
        val projectilesToRemove = mutableListOf<Projectile>()
        val enemiesToRemove = mutableListOf<Enemy>()

        for (p in projectiles) {
            for (e in enemies) {
                if (p.hitPoint.x > e.skin.x && p.hitPoint.x < e.skin.x + e.skin.width
                    && p.hitPoint.y > e.skin.y && p.hitPoint.y < e.skin.y + e.skin.height
                ) {
                    projectilesToRemove.add(p)
                    enemiesToRemove.add(e)
                    e.explode()
                    p.disappear()
                    changeScore()
                }
            }
        }

        projectiles.removeAll(projectilesToRemove)
        enemies.removeAll(enemiesToRemove)
    }
}
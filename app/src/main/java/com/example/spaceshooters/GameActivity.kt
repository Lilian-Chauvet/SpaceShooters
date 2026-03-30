package com.example.spaceshooters

import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.preference.PreferenceManager
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

    override fun onStop() {
        mySensorManager.stop()

        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = preferences.edit()
        editor.putInt("score", score)
        editor.apply()
        super.onStop()
    }

    private fun startGame() {
        blaster = Blaster(this)
        changeScore(10)
        gameLoop()
    }

    private fun spawnEnemies() {
        val spawnRate = 0.01f + (score * 0.0001f)
        var speed = (score * 0.04f)
        if (speed < 2f) {
            speed = 2f
        }
        if (Math.random() < spawnRate) {
            val enemy = Enemy(this, speed)
            enemies.add(enemy)
            enemy.attack()
        }
    }

    fun changeScore(value: Int = 1) {
        if (value > 0) {
            score += (value + score * 0.01f).toInt()
        } else {
            score += (value + score * 0.005f).toInt()
        }

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
            if (score < 0) {
                gameOver()
            }
            val projectilesToRemove = mutableListOf<Projectile>()
            val enemiesToRemove = mutableListOf<Enemy>()
            spawnEnemies()


            // déplacer projectiles
            for (p in projectiles) {
                p.update()

                //retirer les projectiles hors écran
                if (p.hitPoint.y < 0 || p.hitPoint.x > gameArea.width
                    || p.hitPoint.x < 0
                ) {
                    projectilesToRemove.add(p)
                    p.disappear()
                    changeScore(-5)
                }
            }

            // déplacer ennemis
            for (e in enemies) {
                e.update()
                if (e.skin.y > gameArea.height) {
                    enemiesToRemove.add(e)
                    e.disappear()
                    changeScore(-40)
                }
            }

            // collisions
            checkCollisions()

            projectiles.removeAll(projectilesToRemove)
            enemies.removeAll(enemiesToRemove)
        }

        gameArea.postDelayed({ gameLoop() }, 16)
    }

    private fun gameOver() {
        finish()
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
                    continue
                }
            }
        }

        projectiles.removeAll(projectilesToRemove)
        enemies.removeAll(enemiesToRemove)
    }
}
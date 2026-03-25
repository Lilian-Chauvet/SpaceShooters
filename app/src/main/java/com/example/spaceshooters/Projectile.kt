package com.example.spaceshooters

import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView

class Projectile(pContext: GameActivity, angle: Float) {
    var SPEED = 20f
    val context = pContext
    val skin: ImageView = ImageView(pContext)
    val rad = Math.toRadians(angle.toDouble())
    val vx = (Math.sin(rad) * SPEED).toFloat()
    val vy = (Math.cos(rad) * SPEED * (-1)).toFloat()


    fun display(){
        skin.setImageResource(R.drawable.projectile)

        val width = (context.blaster.getSkin().width * 0.1f).toInt()
        val height = width

        val params = ViewGroup.LayoutParams(width, height)
        skin.layoutParams = params

        context.gameArea.addView(skin)
    }

    fun launch(x: Float, y: Float) {
        display()

        skin.x = x
        skin.y = y
        skin.rotation = context.blaster.getSkin().rotation

        //lancer la boucle pour modifier l'affichage
        updateUI()
    }

    private fun updateUI() {
        skin.post(object : Runnable {
            override fun run() {
                skin.x += vx
                skin.y += vy

                skin.postDelayed(this, 16)
            }
        })
    }
}

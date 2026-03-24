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

    init{
        display()
        Log.i("projectile", "vx : "+vx.toString())
        Log.i("projectile", "vy : "+vy.toString())
    }

    fun display(){
        //afficher l'image
        skin.setImageResource(R.drawable.projectile)
        context.gameArea.addView(skin)

        //Adapter la taille du projectile au canon (10% largeur)
        skin.post{
            //taille de l'image
            val ratio = skin.height / skin.width

            val width = (context.blaster.getSkin().width * 0.1f).toInt()
            val height = (width * ratio)
            skin.layoutParams = ViewGroup.LayoutParams(width, height)
        }
    }

    fun launch(x: Float, y: Float) {
        skin.x = x
        skin.y = y

        Log.i("projectile", "x : "+skin.x.toString())
        Log.i("projectile", "y : "+skin.y.toString())

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

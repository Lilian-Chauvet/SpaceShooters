package com.example.spaceshooters

import android.view.ViewGroup
import android.widget.ImageView

class Enemy(pContext: GameActivity) {

    val context = pContext
    private val skin: ImageView = ImageView(pContext)

    fun attack(){
        display()
        val metrics = context.resources.displayMetrics
        val screenWidth = metrics.widthPixels

        skin.x = (Math.random() * (screenWidth - skin.width)).toFloat()

        updateUI()
    }

    fun display(){
        //afficher l'image
        skin.setImageResource(R.drawable.enemy)

        //Récupérer la taille de l'écran
        val metrics = context.resources.displayMetrics
        val screenWidth = metrics.widthPixels

        val width = (screenWidth * 0.2f).toInt()

        val params = ViewGroup.LayoutParams(width, width)
        skin.layoutParams = params

        context.gameArea.addView(skin)
    }

    private fun updateUI() {
        skin.post(object : Runnable {
            override fun run() {
                skin.y += 2

                skin.postDelayed(this, 16)
            }
        })
    }
}

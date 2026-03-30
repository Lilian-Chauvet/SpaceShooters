package com.example.spaceshooters

import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView

class Enemy(pContext: GameActivity, pSpeed: Float = 2f) : GameObject(pContext){
    var speed = pSpeed

    fun attack() {
        display()
        val metrics = context.resources.displayMetrics
        val screenWidth = metrics.widthPixels
        skin.post { skin.x = (Math.random() * (screenWidth - skin.width)).toFloat() }
    }

    override fun display() {
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

    override fun update() {
        skin.y += speed.toInt()
    }

    fun explode() {
        // sauvegarder position et taille
        val x = skin.x
        val y = skin.y
        val width = skin.width
        val height = skin.height

        //  supprimer ancien skin
        context.gameArea.removeView(skin)

        val explosionView = ImageView(context)

        val params = ViewGroup.LayoutParams(width, height)
        explosionView.layoutParams = params

        explosionView.x = x
        explosionView.y = y

        // l'animation
        explosionView.setImageResource(R.drawable.explosion_anim)
        context.gameArea.addView(explosionView)
        val anim = explosionView.drawable as android.graphics.drawable.AnimationDrawable
        anim.start()
        val duration = anim.numberOfFrames * 100L // 100ms par frame
        explosionView.postDelayed({
            context.gameArea.removeView(explosionView)
        }, duration)
    }
}

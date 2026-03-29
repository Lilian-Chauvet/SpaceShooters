package com.example.spaceshooters

import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView

class Enemy(pContext: GameActivity) {

    val context = pContext
    val skin: ImageView = ImageView(pContext)

    fun attack() {
        display()
        val metrics = context.resources.displayMetrics
        val screenWidth = metrics.widthPixels
        skin.post { skin.x = (Math.random() * (screenWidth - skin.width)).toFloat() }
    }

    fun display() {
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

    fun update() {
        skin.y += 2
    }

    fun explode() {
        // 1. Sauvegarder position et taille
        val x = skin.x
        val y = skin.y
        val width = skin.width
        val height = skin.height

        // 2. Supprimer l'ancien skin
        context.gameArea.removeView(skin)

        // 3. Créer une nouvelle ImageView pour l'explosion
        val explosionView = ImageView(context)

        // 4. Appliquer taille identique
        val params = ViewGroup.LayoutParams(width, height)
        explosionView.layoutParams = params

        // 5. Positionner au même endroit
        explosionView.x = x
        explosionView.y = y

        // 6. Appliquer l'animation
        explosionView.setImageResource(R.drawable.explosion_anim)

        // 7. Ajouter à l'écran
        context.gameArea.addView(explosionView)

        // 8. Lancer l'animation
        val anim = explosionView.drawable as android.graphics.drawable.AnimationDrawable
        anim.start()

        // 9. Supprimer après animation
        val duration = anim.numberOfFrames * 100L // 100ms par frame
        explosionView.postDelayed({
            context.gameArea.removeView(explosionView)
        }, duration)
    }
}

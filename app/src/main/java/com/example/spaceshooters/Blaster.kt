package com.example.spaceshooters

import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView

class Blaster(pContext: GameActivity) {

    val context = pContext
    private val skin: ImageView = ImageView(pContext)

    init{
        display()
        skin.setOnClickListener{
            shoot()
        }
    }

    fun getSkin(): ImageView {
        return skin
    }

    fun display(){
        //afficher l'image
        skin.setImageResource(R.drawable.blaster)
        context.gameArea.addView(skin)

        //Récupérer la taille de l'écran
        val metrics = context.resources.displayMetrics
        val screenWidth = metrics.widthPixels
        val screenHeight = metrics.heightPixels

        skin.post{
            //taille de l'image
            val ratio = skin.height / skin.width

            val blasterWidth = (screenWidth * 0.3f).toInt()
            val blasterHeight = (blasterWidth * ratio)
            skin.layoutParams = ViewGroup.LayoutParams(blasterWidth, blasterHeight)

            //positionnement en bas au centre
            skin.x = ((screenWidth / 2) - (blasterWidth/2)).toFloat()
            skin.y = (screenHeight - (blasterHeight/2)).toFloat()
            skin.y = skin.y - (blasterHeight*0.1f)
        }
    }

    fun shoot(){
        //définir la direction du tir
        val angle = skin.rotation

        //créer un nouveau projectile
        val projectile = Projectile(context, angle)

        //calculer les coordonées de départ
        val x = skin.x + (skin.width/2)
        val y = skin.y
        //tirer
        projectile.launch(x, y)

    }

    fun rotate(azimuth: Float) {
        if (azimuth < 90f || azimuth > -90f) {
    }


}
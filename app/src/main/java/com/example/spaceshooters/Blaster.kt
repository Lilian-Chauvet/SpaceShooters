package com.example.spaceshooters

import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView

class Blaster(pContext: GameActivity) {

    val context = pContext
    val skin: ImageView = ImageView(pContext)

    fun display(){
        //afficher l'image
        skin.setBackgroundResource(R.drawable.blaster)
        context.gameArea.addView(skin)

        //Récupérer la taille de l'écran
        val metrics = context.resources.displayMetrics
        val screenWidth = metrics.widthPixels
        val screenHeight = metrics.heightPixels

        //taille de l'image
        val ratio = skin.height / 200
        Log.i("taille", "height"+skin.height.toString())
        Log.i("taille", skin.width.toString())

        val blasterWidth = (screenWidth * 0.2f).toInt()
        val blasterHeight = (blasterWidth * ratio)
        skin.layoutParams = ViewGroup.LayoutParams(blasterWidth, blasterHeight)
        /*
        //positionnement en bas au centre
        skin.x = ((screenWidth / 2) - (skin.width/2)).toFloat()
        skin.y = (screenHeight - (skin.height/2)).toFloat()
         */
    }
}
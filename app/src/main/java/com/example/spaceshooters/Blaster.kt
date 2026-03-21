package com.example.spaceshooters

import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView

class Blaster(pContext: GameActivity) {

    val context = pContext
    val skin: ImageView = ImageView(pContext)

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

            Log.i("taille", "width: ${skin.width} height: ${skin.height}")
            //positionnement en bas au centre
            skin.x = ((screenWidth / 2) - (blasterWidth/2)).toFloat()
            skin.y = (screenHeight - (blasterHeight/2)).toFloat()
            skin.y = skin.y - (blasterHeight*0.1f)
        }
    }

    fun shoot(){

    }
}
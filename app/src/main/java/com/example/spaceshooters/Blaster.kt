package com.example.spaceshooters

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.Image
import android.widget.ImageView
import androidx.core.graphics.drawable.toDrawable

class Blaster(pContext: GameActivity) {

    val context = pContext
    val skin: ImageView = ImageView(pContext)

    fun display(pX: Int, pY: Int){
        skin.setBackgroundResource(R.drawable.blaster)
        context.gameArea.addView(skin)
        skin.x = pX.toFloat()
        skin.y = pY.toFloat()
    }
}
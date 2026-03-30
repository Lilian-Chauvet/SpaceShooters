package com.example.spaceshooters

import android.widget.ImageView

abstract class GameObject(pContext: GameActivity) {
    val skin: ImageView = ImageView(pContext)
    val context = pContext

    abstract fun display()
    abstract fun update()

    open fun disappear() {
        context.gameArea.removeView(skin)
    }
}
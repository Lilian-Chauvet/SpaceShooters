package com.example.spaceshooters

import android.graphics.Point
import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import androidx.graphics.shapes.MutablePoint

class Projectile(pContext: GameActivity, angle: Float) : GameObject(pContext) {
    var SPEED = 20f
    val rad = Math.toRadians(angle.toDouble())
    val vx = (Math.sin(rad) * SPEED).toFloat()
    val vy = (Math.cos(rad) * SPEED * (-1)).toFloat()
    val hitPoint = Point(0, 0)


    override fun display() {
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

        hitPoint.x = skin.x.toInt() + (skin.width / 2)
        hitPoint.y = skin.y.toInt()
        skin.rotation = context.blaster.getSkin().rotation
    }

    override fun update() {
        skin.x += vx
        skin.y += vy

        hitPoint.x = skin.x.toInt()
        hitPoint.y = skin.y.toInt()
    }
}

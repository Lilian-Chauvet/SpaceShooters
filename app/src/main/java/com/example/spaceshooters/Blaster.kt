package com.example.spaceshooters

import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView

class Blaster(pContext: GameActivity) {

    val context = pContext
    private val skin: ImageView = ImageView(pContext)
    var rotationMode = 1

    init{
        display()
        skin.setOnLongClickListener {
            resetRotation()
            true
        }
    }

    private fun resetRotation() {
        skin.rotation = 0f
    }

    fun getSkin(): ImageView {
        return skin
    }

    fun display(){
        //afficher l'image
        skin.setImageResource(R.drawable.blaster)

        //Récupérer la taille de l'écran
        val metrics = context.resources.displayMetrics
        val screenWidth = metrics.widthPixels
        val screenHeight = metrics.heightPixels

        val width = (screenWidth * 0.5f).toInt()

        val params = ViewGroup.LayoutParams(width, width)
        skin.layoutParams = params

        context.gameArea.addView(skin)

        //positionnement en bas au centre
        skin.x = ((screenWidth / 2) - (width/2)).toFloat()
        skin.y = (screenHeight - (width/2)).toFloat()
        skin.y = skin.y - (width*0.1f)
    }

    fun shoot() {
        // direction du tir
        val angleDeg = skin.rotation
        val angleRad = Math.toRadians(angleDeg.toDouble())

        // point de base du bout du canon (non tourné)
        val x0 = skin.x + (skin.width / 2f)
        val y0 = skin.y

        // centre de l'image
        val cx = skin.x + (skin.width / 2f)
        val cy = skin.y + (skin.height / 2f)

        //décaler par rapport au centre de l'image
        val dx = x0 - cx
        val dy = y0 - cy

        //rotation 2D
        val cos = Math.cos(angleRad)
        val sin = Math.sin(angleRad)
        val dxRot = dx * cos - dy * sin
        val dyRot = dx * sin + dy * cos

        //revenir aux coordonnées écran
        val xCanon = cx + dxRot
        val yCanon = cy + dyRot

        // créer le projectile
        val projectile = Projectile(context, angleDeg)
        context.projectiles.add(projectile)


        // lancer le projectile depuis le bout du canon
        projectile.launch(xCanon.toFloat(), yCanon.toFloat())
    }


    fun rotate(rotationDegree: Float?) {
        if (rotationDegree != null) {
            val delta = rotationMode * rotationDegree
            val newRotation = skin.rotation + delta

            if (newRotation < 90 && newRotation > -90) {
                skin.rotation = newRotation
            }
        }
    }


}
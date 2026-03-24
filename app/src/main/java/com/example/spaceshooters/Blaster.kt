package com.example.spaceshooters

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

        // lancer le projectile depuis le bout du canon
        projectile.launch(xCanon.toFloat(), yCanon.toFloat())
    }


    fun rotate(rotation: Float?) {
        if (rotation != null) {
            if (skin.rotation+rotation < 90 && skin.rotation+rotation > -90)
            skin.rotation += rotation
        }
    }


}
package com.example.spaceshooters

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log

class MySensorManager (pContext: GameActivity) : SensorEventListener {

    private val context         = pContext
    private val sensorManager   = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val rotationVector  = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)



    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val rotationMatrix = FloatArray(9)
        SensorManager.getRotationMatrixFromVector(rotationMatrix, event.values)

        val orientations = FloatArray(3)
        SensorManager.getOrientation(rotationMatrix, orientations)

        //rotation à plat (style boussole)
        val azimuth = Math.toDegrees(orientations[0].toDouble()).toFloat()
        Log.i("azimuth", azimuth.toString())

        context.blaster.rotate(azimuth)
    }

    fun start() {
        sensorManager.registerListener(this, rotationVector, SensorManager.SENSOR_DELAY_GAME)
    }

    fun stop(){
        sensorManager.unregisterListener(this)
    }
}
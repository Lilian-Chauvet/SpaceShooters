package com.example.spaceshooters

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

class MySensorManager (pContext: GameActivity) : SensorEventListener {

    private val context         = pContext
    private val sensorManager   = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val rotationVector  = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)



    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val rotationZ = event?.values[2] // axe Z (rotation écran)

        context.blaster.rotate(rotationZ)
    }

    fun start() {
        sensorManager.registerListener(this, rotationVector, SensorManager.SENSOR_DELAY_GAME)
    }

    fun stop(){
        sensorManager.unregisterListener(this)
    }
}
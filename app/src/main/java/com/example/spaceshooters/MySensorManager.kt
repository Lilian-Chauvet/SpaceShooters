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

    fun displaySensors() {
        val deviceSensors: List<Sensor> = sensorManager.getSensorList(Sensor.TYPE_ALL)

        for (s in deviceSensors){
            Log.i("sensor", s.name)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onSensorChanged(event: SensorEvent?) {
    }
}
package com.example.spaceshooters

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var btn_play: Button
    private lateinit var btn_options: Button
    private lateinit var btn_credits: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btn_play = findViewById(R.id.btn_play)
        btn_play.setOnClickListener(this)

        btn_options = findViewById(R.id.btn_options)
        btn_options.setOnClickListener(this)

        btn_credits = findViewById(R.id.btn_credits)
        btn_credits.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v?.id == btn_play.id) {
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
        }
        if (v?.id == btn_options.id) {
            val intent = Intent(this, OptionsActivity::class.java)
            startActivity(intent)
        }
        if (v?.id == btn_credits.id) {
            val intent = Intent(this, CreditsActivity::class.java)
            startActivity(intent)
        }
    }
}
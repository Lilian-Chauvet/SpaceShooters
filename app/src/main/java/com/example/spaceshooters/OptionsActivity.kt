package com.example.spaceshooters

import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

class OptionsActivity : AppCompatActivity() {
    lateinit var iv_return: ImageView
    lateinit var radioGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_options)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        iv_return = findViewById(R.id.iv_return)
        iv_return.setOnClickListener {
            finish()
        }
        radioGroup = findViewById(R.id.rg_language)
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rb_en -> {
                    // mettre anglais
                    setLocale("en")
                }
                R.id.rb_fr -> {
                    // mettre français
                    setLocale("fr")
                }
            }
        }
    }

    fun setLocale(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)

        // Recréer l'activité pour appliquer les changements
        recreate()
    }
}
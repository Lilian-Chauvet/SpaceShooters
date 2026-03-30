package com.example.spaceshooters

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class OptionsActivity : AppCompatActivity() {
    lateinit var iv_return: ImageView
    lateinit var radioGroup: RadioGroup
    lateinit var sw_invertRota:SwitchCompat
    var isTouched = false

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

        //Lecture des preferences pour le switchCompat
        val prefs = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val isInverted = prefs.getBoolean("invert_rotation", false)

        sw_invertRota = findViewById(R.id.sw_invertRota)
        sw_invertRota.isChecked = isInverted

        sw_invertRota.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("invert_rotation", isChecked).apply()
        }

        val currentLang = LocaleHelper.getSavedLanguage(this)

        when (currentLang) {
            "fr" -> radioGroup.check(R.id.rb_fr)
            "en" -> radioGroup.check(R.id.rb_en)
        }

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rb_en -> {
                    // mettre anglais
                    changeLanguage("en")
                }
                R.id.rb_fr -> {
                    // mettre français
                    changeLanguage("fr")
                }
            }
        }
    }

    fun changeLanguage(languageCode: String) {
        val current = LocaleHelper.getSavedLanguage(this)
        if (current == languageCode) return

        LocaleHelper.setLocale(this, languageCode)
        recreate()
    }

    override fun attachBaseContext(newBase: Context) {
        val lang = LocaleHelper.getSavedLanguage(newBase)
        val context = LocaleHelper.setLocale(newBase, lang)
        super.attachBaseContext(context)
    }
}
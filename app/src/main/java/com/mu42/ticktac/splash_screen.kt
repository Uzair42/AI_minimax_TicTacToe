package com.mu42.ticktac

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // Load animations
        val topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        val bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)

        // Apply to views
        val logo = findViewById<ImageView>(R.id.logoImage)
        val appName = findViewById<TextView>(R.id.appName)
        val version = findViewById<TextView>(R.id.versionInfo)

        logo.startAnimation(topAnim)
        appName.startAnimation(bottomAnim)
        version.startAnimation(bottomAnim)

        // 3s Delay to move next
        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 3000)
    }
}

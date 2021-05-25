package com.example.finalproject

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.finalproject.databinding.SplashActivityBinding

class SplashActivity: AppCompatActivity() {
    private lateinit var binding : SplashActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        supportActionBar?.hide()
        setContentView(R.layout.splash_activity)
        binding = DataBindingUtil.setContentView(this ,R.layout.splash_activity)
        AminationSplash()
        binding.apply {
            btnNext.setOnClickListener{
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            }
        }
    }
    private fun AminationSplash()
    {
        binding.apply {
            var animation_image = AnimationUtils.loadAnimation(this@SplashActivity,R.anim.top_animation)
            imgDisk.animation = animation_image

            var animation_welcome = AnimationUtils.loadAnimation(this@SplashActivity,R.anim.bottom_animation)
            tvWelcome.animation = animation_welcome

            var animation_textNnext = AnimationUtils.loadAnimation(this@SplashActivity,R.anim.right_animation)
            tvNext.animation = animation_textNnext

            var animation_buttonNext = AnimationUtils.loadAnimation(this@SplashActivity,R.anim.left_animation)
            btnNext.animation = animation_buttonNext
        }
    }
}
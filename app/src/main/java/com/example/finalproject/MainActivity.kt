package com.example.finalproject

import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.commit
import com.example.finalproject.databinding.ActicityMainBinding
import androidx.fragment.app.add
import androidx.fragment.app.replace

class MainActivity: AppCompatActivity() {
    private lateinit var binding: ActicityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        supportActionBar?.hide()
        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.acticity_main)

    }
    override fun onStart() {
        super.onStart()
        Log.e("onStart", " ")
    }
    override fun onResume() {
        super.onResume()
        Log.e("onResume", " ")
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<LogInFragment>(R.id.fragment_container_view)
        }
    }
}
package com.example.bt1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.bt1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding  : ActivityMainBinding // setup binding
    private lateinit var  viewmodel: MainViewmodel // setup viewmodel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewmodel = ViewModelProvider(this).get(MainViewmodel::class.java)
        //set up viewwholder

        val adapter  = IdolAdapter() // tao adapter
        binding.rclist.adapter = adapter // gan adapter
        adapter.data = getDataSet()
    }
}
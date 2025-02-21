package com.food.carnival.merge

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<vb : ViewBinding> : AppCompatActivity() {
    protected lateinit var binding: vb

    abstract fun getViewBinding(): vb
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
    }

}
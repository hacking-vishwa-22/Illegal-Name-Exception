package com.zetas.kidneycare.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zetas.kidneycare.R
import com.zetas.kidneycare.databinding.ActivityAwareBinding

class AwareActivity : AppCompatActivity() {
    lateinit var binding:ActivityAwareBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAwareBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}
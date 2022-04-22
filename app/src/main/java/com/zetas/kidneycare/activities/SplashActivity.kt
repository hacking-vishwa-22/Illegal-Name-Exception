package com.zetas.kidneycare.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.zetas.kidneycare.MainActivity
import com.zetas.kidneycare.R
import com.zetas.kidneycare.databinding.ActivitySplashBinding
import kotlinx.coroutines.*

class SplashActivity : AppCompatActivity() {

    lateinit var binding: ActivitySplashBinding
    lateinit var animation: Animation
    lateinit var auth:FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        auth = FirebaseAuth.getInstance()


        animation = AnimationUtils.loadAnimation(this, R.anim.fade_in)

        binding.splash.animation = animation

        GlobalScope.launch {

            delay(3000L)
            if(auth.currentUser!=null){
                withContext(Dispatchers.Main){
                    Toast.makeText(this@SplashActivity,"Welcome Back!",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@SplashActivity, AwareActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }else{
                withContext(Dispatchers.Main){
                    Toast.makeText(this@SplashActivity,"Please Login",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }



        }


    }
}
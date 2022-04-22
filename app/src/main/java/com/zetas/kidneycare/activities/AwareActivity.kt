package com.zetas.kidneycare.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.zetas.kidneycare.R
import com.zetas.kidneycare.databinding.ActivityAwareBinding
import kotlinx.coroutines.GlobalScope

class AwareActivity : AppCompatActivity() {
    lateinit var user: FirebaseUser
    lateinit var binding: ActivityAwareBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAwareBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //firebase user details
        user = FirebaseAuth.getInstance().currentUser!!

        //display of user details
        binding.userName.text = "Hello, ${user.displayName}"
        binding.userEmail.text = user.email
        Glide.with(this).load(user.photoUrl.toString()).into(binding.userImage)

        //closing popup acc to demand
        binding.closePopUp.setOnClickListener {
            binding.ll2.visibility = View.GONE
        }


    }
}
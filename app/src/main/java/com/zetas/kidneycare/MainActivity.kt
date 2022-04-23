package com.zetas.kidneycare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val sharedPreferences = getSharedPreferences("UserDetails", MODE_PRIVATE)
//        val USER_EMAIL = sharedPreferences.getString("USER_EMAIL","")
//        val USER_NAME = sharedPreferences.getString("USER_NAME","")
//        val USER_PHONE = sharedPreferences.getString("USER_PHONE","XXXXXXXXXX")
//        val USER_PROFILE = sharedPreferences.getString("USER_PROFILE","")
//        Toast.makeText(this,"name : $USER_NAME \n phone = $USER_PHONE",Toast.LENGTH_SHORT).show()

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}
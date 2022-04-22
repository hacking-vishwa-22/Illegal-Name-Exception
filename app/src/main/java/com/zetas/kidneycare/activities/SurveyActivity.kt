package com.zetas.kidneycare.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.zetas.kidneycare.R
import com.zetas.kidneycare.fragments.DetailsFragment

class SurveyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey)
        changeFragment(DetailsFragment())
    }
    private fun changeFragment(fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayoutSurvey,fragment)
        fragmentTransaction.commit()
    }
}
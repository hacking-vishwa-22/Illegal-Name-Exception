package com.zetas.kidneycare.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.zetas.kidneycare.R
import com.zetas.kidneycare.fragments.DashboardFragment
import com.zetas.kidneycare.fragments.DetailsFragment

class SurveyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO) //forcing light theme

        val checker = intent.getStringExtra("checker")
        if (checker.equals("YES")) {
            changeFragment(DetailsFragment())
        } else {
            changeFragment(DetailsFragment())
        }

    }

    private fun changeFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayoutSurvey, fragment)
        fragmentTransaction.commit()
    }
}
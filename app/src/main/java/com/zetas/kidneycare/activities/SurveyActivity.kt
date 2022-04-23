package com.zetas.kidneycare.activities

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.zetas.kidneycare.R
import com.zetas.kidneycare.fragments.DashboardFragment
import com.zetas.kidneycare.fragments.DetailsFragment

class SurveyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO) //forcing light theme

        permissionRequest()

        val checker = intent.getStringExtra("checker")
        if (checker.equals("YES")) {
            changeFragment(DetailsFragment())
        } else {
            changeFragment(DetailsFragment())
        }

    }

    private fun changeFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
        fragmentTransaction.replace(R.id.frameLayoutSurvey, fragment)
        fragmentTransaction.commit()
    }

    override fun onBackPressed() {
        if (R.id.frameLayoutSurvey == DashboardFragment().id) {
            super.onBackPressed()
            Toast.makeText(this, "lllll", Toast.LENGTH_SHORT).show()
        } else {
            changeFragment(DashboardFragment())
        }
    }

    fun hasCoarseLocationPermission() = ActivityCompat.checkSelfPermission(
        this,
        Manifest.permission.ACCESS_COARSE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

    fun hasFineLocationPermission() = ActivityCompat.checkSelfPermission(
        this,
        Manifest.permission.ACCESS_COARSE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

    fun permissionRequest() {
        var permissions = mutableListOf<String>()

        if (!hasCoarseLocationPermission()) {
            permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION)
        }
        if (!hasFineLocationPermission()) {
            permissions.add(Manifest.permission.ACCESS_FINE_LOCATION)
        }
        if (permissions.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, permissions.toTypedArray(), 0)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 0 && grantResults.isNotEmpty()) {
            for (i in grantResults.indices) {
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    Toast.makeText(
                        this,
                        "You cannot look at your nearest dialysis location without giving location",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Log.d("PERMISSION", "Granted")
                }
            }
        }
    }
}
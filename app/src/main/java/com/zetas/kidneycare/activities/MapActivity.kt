package com.zetas.kidneycare.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.zetas.kidneycare.R
import com.zetas.kidneycare.databinding.ActivityMapBinding
import com.zetas.kidneycare.fragments.MapFragment

class MapActivity : AppCompatActivity() {
    lateinit var googleMap:GoogleMap
    lateinit var binding: ActivityMapBinding
    //lateinit var frmeLayout: FrameLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        val view: View = binding.root
        //frmeLayout = binding.mapFrameLayout
        setContentView(view)//mapFrameLayout
        val mFragment = MapFragment()
        changeFragment(mFragment)

    }
    private fun changeFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.apply {
            replace(R.id.mapFrameLayout, fragment)
            commit()
        }
    }
}
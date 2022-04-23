package com.zetas.kidneycare.fragments

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.zetas.kidneycare.R
import com.zetas.kidneycare.databinding.FragmentMapBinding

class MapFragment : Fragment() {
    lateinit var binding: FragmentMapBinding
    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */

        val city = LatLng(22.8015194, 86.2029579)
        val latLng1 = LatLng(22.83271164747722, 86.09271154234668)
        val latLng2 = LatLng(22.85106280337921, 86.19193182127769)
        val latLng3 = LatLng(22.80676268705106, 86.2482367546503)
        val latLng4 = LatLng(22.79220378877773, 86.21630773755487)
        val latLng5 = LatLng(22.81271856704312, 86.19729280773845)
        val latLng6 = LatLng(22.815883200769942, 86.22475862889583)

        googleMap.addMarker(MarkerOptions().position(city).title("Your Location"))
        googleMap.addMarker(MarkerOptions().position(latLng1).title("Centre 1"))
        googleMap.addMarker(MarkerOptions().position(latLng2).title("Centre 2"))
        googleMap.addMarker(MarkerOptions().position(latLng3).title("Centre 3"))
        googleMap.addMarker(MarkerOptions().position(latLng4).title("Centre 4"))
        googleMap.addMarker(MarkerOptions().position(latLng5).title("Centre 5"))
        googleMap.addMarker(MarkerOptions().position(latLng6).title("Centre 6"))

//        private val hopspitalIcon: BitmapDescriptor by lazy {
//            val color = ContextCompat.getColor(this, R.color.teal_200)
//            BitmapHelper.vectorToBitmap(this, R.drawable.ic_directions_bike_black_24dp, color)
//        }
//        val markerDialysis1 = googleMap.addMarker(
//            MarkerOptions()
//                .title("Dialysis Centre")
//                .position(latLng1)
//                .icon(hopspitalIcon)
//        )
//        val markerDialysis2 = googleMap.addMarker(
//            MarkerOptions()
//                .title("Dialysis Centre")
//                .position(latLng2)
//                .icon(hopspitalIcon)
//        )
//        val markerDialysis3 = googleMap.addMarker(
//            MarkerOptions()
//                .title("Dialysis Centre")
//                .position(latLng3)
//                .icon(hopspitalIcon)
//        )
//        val markerDialysis4 = googleMap.addMarker(
//            MarkerOptions()
//                .title("Dialysis Centre")
//                .position(latLng4)
//                .icon(hopspitalIcon)
//        )
//        val markerDialysis5 = googleMap.addMarker(
//            MarkerOptions()
//                .title("Dialysis Centre")
//                .position(latLng5)
//                .icon(hopspitalIcon)
//        )
//        val markerDialysis6 = googleMap.addMarker(
//            MarkerOptions()
//                .title("Dialysis Centre")
//                .position(latLng6)
//                .icon(hopspitalIcon)
//        )
//
//        googleMap.addMarker(MarkerOptions().position(city).title("Marker in Jamshedpur"))
//        googleMap.addMarker(
//                MarkerOptions()
//                    .title("Dialysis Centre")
//                    .position(latLng2)
//                    .icon(hopspitalIcon)
//            )
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(city))
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}
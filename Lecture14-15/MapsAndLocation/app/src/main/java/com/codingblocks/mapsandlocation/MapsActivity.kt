package com.codingblocks.mapsandlocation

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, LocationListener {

    lateinit var locList: LocationListener
    override fun onLocationChanged(p0: Location) {
        if (mMap != null)
            mMap.animateCamera(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(p0.latitude, p0.longitude),
                    22f
                )
            )
    }

override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

override fun onProviderEnabled(p0: String?) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

override fun onProviderDisabled(p0: String?) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

private lateinit var mMap: GoogleMap

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_maps)
    // Obtain the SupportMapFragment and get notified when the map is ready to be used.
    val mapFragment = supportFragmentManager
        .findFragmentById(R.id.map) as SupportMapFragment
    mapFragment.getMapAsync(this)
    locList = this
    if (ActivityCompat.checkSelfPermission(
            this,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
            1234
        )
    } else {
        startLocationUpdates()
    }
}

@SuppressLint("MissingPermission")
private fun startLocationUpdates() {
    val locMan = getSystemService(Context.LOCATION_SERVICE) as LocationManager
    locMan.requestLocationUpdates(
        LocationManager.GPS_PROVIDER,
        1000, 0f, locList
    )
}

/**
 * Manipulates the map once available.
 * This callback is triggered when the map is ready to be used.
 * This is where we can add markers or lines, add listeners or move the camera. In this case,
 * we just add a marker near Sydney, Australia.
 * If Google Play services is not installed on the device, the user will be prompted to install
 * it inside the SupportMapFragment. This method will only be triggered once the user has
 * installed Google Play services and returned to the app.
 */
override fun onMapReady(googleMap: GoogleMap) {
    mMap = googleMap

    // Add a marker in Sydney and move the camera
    val sydney = LatLng(-34.0, 151.0)
    mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
    mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
}
}

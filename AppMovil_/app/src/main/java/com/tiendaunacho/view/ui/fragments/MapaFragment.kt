package com.tiendaunacho.view.ui.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.tiendaunacho.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import com.google.type.LatLng


class MapaFragment : Fragment(), OnMapReadyCallback {
    private lateinit var googleMap:GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_mapa, container, false)
        val mapFragment=this.childFragmentManager.findFragmentById(R.id.map_view) as SupportMapFragment
        mapFragment.getMapAsync(this)
        return view

    }

    override fun onMapReady(map: GoogleMap) {
        val colombia= com.google.android.gms.maps.model.LatLng(4.570868, -74.297333)
        val Yaguara_Huila= com.google.android.gms.maps.model.LatLng(2.667, -75.517)
        map?.let {
            this.googleMap=it
            map.addMarker(MarkerOptions().position(colombia))
            map.addMarker(MarkerOptions().position(Yaguara_Huila))
        }
        enableLocation()

    }


    private fun isLocationPermissionGrated()=ContextCompat.checkSelfPermission(this.requireContext(),
        Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED

    @SuppressLint("MissingPermission")
    private fun enableLocation(){
        if (!::googleMap.isInitialized)return
        if (isLocationPermissionGrated()){
            googleMap.isMyLocationEnabled=true
        }else{
            requestLocationPermission()
        }
    }
    companion object{
        const val REQUEST_CODE_LOCATION=0
    }

    private fun requestLocationPermission() {
        if(ActivityCompat.shouldShowRequestPermissionRationale(
            this.requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION
        )){
            Toast.makeText(context, "requiere activar permisos en ajustes", Toast.LENGTH_SHORT).show()
        }else{
            ActivityCompat.requestPermissions(this.requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_CODE_LOCATION)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            REQUEST_CODE_LOCATION ->
                if(grantResults.isNotEmpty()&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    googleMap.isMyLocationEnabled
                }else{
                    Toast.makeText(context, "Para activar la localización ve a ajustes y acepta los permisos", Toast.LENGTH_SHORT).show()
                }else->{}
        }
    }


}
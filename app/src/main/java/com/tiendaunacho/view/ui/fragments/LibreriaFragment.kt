package com.tiendaunacho.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tiendaunacho.R
import com.tiendaunacho.view.adapter.LibreryAdapter


class LibreriaFragment : Fragment() {
    lateinit var recycleLib:RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_libreria, container, false)
        recycleLib=view.findViewById(R.id.recyclerview)
        val adapter=LibreryAdapter()
        recycleLib.layoutManager=LinearLayoutManager(context)
        recycleLib.adapter=adapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btm=view.findViewById<BottomNavigationView>(R.id.buttonnvigation)
        btm.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.home -> findNavController().navigate(R.id.action_libreriaFragment_to_homeFragment)
                R.id.perfilpersona -> findNavController().navigate(R.id.action_libreriaFragment_to_perfilFragment)
                R.id.map -> findNavController().navigate(R.id.action_libreriaFragment_to_mapaFragment)
            }

        }
    }

}

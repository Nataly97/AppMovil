package com.tiendaunacho.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.tiendaunacho.R
import com.tiendaunacho.view.adapter.LibreryAdapter
import com.tiendaunacho.viewmodel.LibraryViewModel


class LibreriaFragment : Fragment() {
    lateinit var recycleLib:RecyclerView
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var adapter: LibreryAdapter
    private val viewModel by lazy { ViewModelProvider(this).get(LibraryViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAuth= Firebase.auth
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_libreria, container, false)
        recycleLib=view.findViewById(R.id.recyclerview)
        adapter=LibreryAdapter(requireContext())
        recycleLib.layoutManager=LinearLayoutManager(context)
        recycleLib.adapter=adapter
        observeData()
        return view
    }
    fun observeData(){
        viewModel.libraryData().observe(viewLifecycleOwner, Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btm=view.findViewById<BottomNavigationView>(R.id.buttonnavigation)
        btm.setOnNavigationItemReselectedListener {
            when(it.itemId) {
                R.id.home -> findNavController().navigate(R.id.action_libreriaFragment_to_homeFragment)
                R.id.perfilpersona -> findNavController().navigate(R.id.action_libreriaFragment_to_perfilFragment)
                R.id.map -> findNavController().navigate(R.id.action_libreriaFragment_to_mapaFragment)
                R.id.cerrar -> {
                    firebaseAuth.signOut()
                    findNavController().navigate(R.id.action_libreriaFragment_to_loginActivity)
                    true
                }
            }
        }
    } 

}

package com.tiendaunacho.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.tiendaunacho.R
import com.tiendaunacho.model.libros
import com.tiendaunacho.view.adapter.LibreryAdapter
import com.tiendaunacho.view.adapter.OnBookItemClickListener
import com.tiendaunacho.viewmodel.LibraryViewModel

@Suppress("DEPRECATION")
class LibreriaFragment : Fragment(), OnBookItemClickListener {
    val database:FirebaseFirestore=FirebaseFirestore.getInstance()
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
        adapter=LibreryAdapter(requireContext(), this)
        recycleLib.layoutManager=LinearLayoutManager(context)
        recycleLib.adapter=adapter
        observeData()
        return view
    }
    fun observeData(){
        viewModel.fetchlibraryData().observe(viewLifecycleOwner, Observer {
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

    override fun onItemclick(libro: libros, position: Int) {
        val titulo:String=libro.titulo
        val precio:String=libro.precio
        val image:String=libro.image
        val dato= hashMapOf(
            "titulo" to titulo,
            "precio" to precio,
            "image" to image
        )
        database.collection("compras")
            .document(titulo)
            .set(dato)
            .addOnSuccessListener {
                Toast.makeText(context, "libro añadido al carrito", Toast.LENGTH_SHORT).show()
            }
    }

}

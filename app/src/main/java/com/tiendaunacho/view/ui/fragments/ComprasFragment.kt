package com.tiendaunacho.view.ui.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
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
import com.tiendaunacho.model.compras
import com.tiendaunacho.view.adapter.ComprasAdapter
import com.tiendaunacho.view.adapter.OnCompraItemClickListener
import com.tiendaunacho.viewmodel.ComprasViewModel

@Suppress("DEPRECATION")
class ComprasFragment : Fragment(), OnCompraItemClickListener{
    lateinit var  recyclerView: RecyclerView
    lateinit var adapter: ComprasAdapter
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var precioT:TextView
    lateinit var compraT:TextView
    val database:FirebaseFirestore=FirebaseFirestore.getInstance()
    private val viewmodel by lazy {ViewModelProvider(this).get(ComprasViewModel::class.java) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_compras, container, false)
        recyclerView=view.findViewById(R.id.recyclerviewcompra)
        precioT=view.findViewById(R.id.preciototal)
        compraT=view.findViewById(R .id.realizar)
        adapter=ComprasAdapter(requireContext(),this)
        recyclerView.layoutManager=LinearLayoutManager(context)
        recyclerView.adapter=adapter
        observeData()
        preciototal()
        compraT.setOnClickListener {
            realizarcompra()
        }
        return view
    }

    private fun observeData() {
        viewmodel.fetchComprasData().observe(viewLifecycleOwner,Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun preciototal(){
        database.collection("compras")
            .get()
            .addOnSuccessListener {
                result->
                val preciounitario= mutableListOf<String>()
                for (document in result){
                    val precio=document["precio"].toString()
                    preciounitario.add(precio!!)
                }
                val preciototal=preciounitario.mapNotNull { it.toIntOrNull()}.sum()
                precioT.setText(Integer.toString(preciototal))
            }

    }

    private fun realizarcompra(){
        val builder=AlertDialog.Builder(requireContext())
        builder.setTitle("CompraUnacholee")
        builder.setMessage("¿Desea realizar esta compra?")
        builder.setPositiveButton("Aceptar"){
            dialog,which->
            findNavController().navigate(R.id.action_comprasFragment_to_homeFragment)
        }
        builder.setNegativeButton("Cancelar",null)
        builder.show()
    }



    override fun onItemclick(libro: compras, position: Int) {
        database.collection("compras")
            .document(libro.titulo)
            .delete()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btm=view.findViewById<BottomNavigationView>(R.id.buttonnavigation)
        btm.setOnNavigationItemReselectedListener {
            when(it.itemId) {
                R.id.home -> findNavController().navigate(R.id.action_comprasFragment_to_homeFragment)
                R.id.perfilpersona -> findNavController().navigate(R.id.action_comprasFragment_to_perfilFragment)
                R.id.map -> findNavController().navigate(R.id.action_comprasFragment_to_mapaFragment)
                R.id.cerrar -> {
                    firebaseAuth.signOut()
                    findNavController().navigate(R.id.action_libreriaFragment_to_loginActivity)
                    true
                }
            }
        }
    }

}
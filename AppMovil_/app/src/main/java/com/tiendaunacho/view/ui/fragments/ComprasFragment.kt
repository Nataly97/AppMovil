package com.tiendaunacho.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.tiendaunacho.R
import com.tiendaunacho.model.compras
import com.tiendaunacho.view.adapter.ComprasAdapter
import com.tiendaunacho.view.adapter.OnCompraItemClickListener
import com.tiendaunacho.viewmodel.ComprasViewModel


class ComprasFragment : Fragment(), OnCompraItemClickListener {
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: ComprasAdapter
    private val viewModel by lazy { ViewModelProvider(this).get(ComprasViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_compras, container, false)
        recyclerView=view.findViewById(R.id.recyclerviewcompra)
        adapter= ComprasAdapter(requireContext(),this)
        recyclerView.layoutManager= LinearLayoutManager(context)
        recyclerView.adapter=adapter
        observeData()
        return view
    }

    private fun observeData() {
        viewModel.fetchComprasData().observe(viewLifecycleOwner, Observer{
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }

    override fun onItemclick(libro: compras, position: Int) {
        TODO("Not yet implemented")
    }

}
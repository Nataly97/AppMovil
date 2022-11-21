package com.tiendaunacho.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.tiendaunacho.R


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_home, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cardlibreria=view.findViewById<ImageView>(R.id.cardLibreria)
        cardlibreria.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_libreriaFragment)
        }

        val cardcarrito=view.findViewById<ImageView>(R.id.cardCarrito)
        cardcarrito.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_comprasFragment)
        }

        val cardperfil=view.findViewById<ImageView>(R.id.cardPerfil)
        cardperfil.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_perfilFragment)
        }

        val cardlista=view.findViewById<ImageView>(R.id.cardLista)
        cardlista.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_deseosFragment)
        }

        val cardpedido=view.findViewById<ImageView>(R.id.cardMapa)
        cardpedido.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_mapaFragment)
        }

        val cardayuda=view.findViewById<ImageView>(R.id.cardAyuda)
        cardayuda.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_ayudaFragment)
        }

    }
}

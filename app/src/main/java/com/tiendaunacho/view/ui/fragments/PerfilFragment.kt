package com.tiendaunacho.view.ui.fragments

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.tiendaunacho.R



class PerfilFragment : Fragment() {
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_perfil, container, false)
        val nombrecompleto=view.findViewById<EditText>(R.id.nombrecompleto)
        val btmeditnombre=view.findViewById<ImageButton>(R.id.edicionnombre)
        nombrecompleto.isEnabled=false
        btmeditnombre.setOnClickListener {
            if(nombrecompleto.isEnabled==false){
                nombrecompleto.isEnabled=true
            }else if (nombrecompleto.isEnabled==true){
                nombrecompleto.isEnabled=false
            }
        }

        val btmcamara = view.findViewById<Button>(R.id.btmcamara)
        btmcamara.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, 123)
        }

        val btmgaleria = view.findViewById<Button>(R.id.btmgaleria)
        btmgaleria.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 456)
        }
        return view

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val imageView = view?.findViewById<ImageView>(R.id.fotoperfil)
        if (requestCode == 123) {
            var bitmap = data?.extras?.get("data") as Bitmap
            imageView?.setImageBitmap(bitmap)
        } else if (requestCode == 456) {
            imageView?.setImageURI(data?.data)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btm=view.findViewById<BottomNavigationView>(R.id.buttonnavigation)
        btm.setOnNavigationItemReselectedListener {
            when(it.itemId) {
                R.id.home -> findNavController().navigate(R.id.action_perfilFragment_to_homeFragment)
                R.id.perfilpersona -> findNavController().navigate(R.id.action_perfilFragment_self)
                R.id.map -> findNavController().navigate(R.id.action_perfilFragment_to_mapaFragment)
                R.id.cerrar -> {
                    firebaseAuth.signOut()
                    findNavController().navigate(R.id.action_libreriaFragment_to_loginActivity)
                    true
                }
            }
        }
    }
}
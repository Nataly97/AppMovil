package com.tiendaunacho.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.tiendaunacho.R

class RecuperarActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    lateinit var RecuperarButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperar)
        firebaseAuth= Firebase.auth
        RecuperarButton=findViewById(R.id.BRestaurar)
        val correo=findViewById<EditText>(R.id.correorecuperar)

        RecuperarButton.setOnClickListener {
            cambiocontrasena(correo.text.toString())
        }

    }

    private fun cambiocontrasena(correo: String){
        firebaseAuth.sendPasswordResetEmail(correo)
            .addOnCompleteListener(this){ task->
                if(task.isSuccessful){
                    Toast.makeText(
                        baseContext,
                        "correo de cambio de contraseña enviado",
                        Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, LoginActivity::class.java))
                }else {
                    Toast.makeText(baseContext,"Error, correo no existente",Toast.LENGTH_SHORT).show()
                }
            }


    }
}
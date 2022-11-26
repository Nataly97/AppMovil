package com.tiendaunacho.view.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.tiendaunacho.R

class LoginActivity : AppCompatActivity() {

    lateinit var homeButton: Button
    lateinit var RegistroButton: Button
    lateinit var RecuperarButton: TextView
    private lateinit var firebaseAuth: FirebaseAuth

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        firebaseAuth= Firebase.auth

        homeButton=findViewById(R.id.Binicio)
        RegistroButton=findViewById(R.id.Bregistro)
        RecuperarButton=findViewById(R.id.Brecuperar)
        val correo=findViewById<EditText>(R.id.correologin)
        val contrasena=findViewById<EditText>(R.id.contrasenalogin)

        homeButton.setOnClickListener {
            login(correo.text.toString(),contrasena.text.toString())
        }
        RegistroButton.setOnClickListener {
            startActivity(Intent(this, RegistroActivity::class.java))
        }
        RecuperarButton.setOnClickListener {
            startActivity(Intent(this, RecuperarActivity::class.java))
        }

    }

    private fun login(correo: String,contrasena:String){
        firebaseAuth.signInWithEmailAndPassword(correo,contrasena)
            .addOnCompleteListener(this) {
                task-> if(task.isSuccessful){
                    val user=firebaseAuth.currentUser
                    Toast.makeText(baseContext,user?.uid.toString(), Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, HomeActivity::class.java))
                }else {
                    Toast.makeText(baseContext,"Por favor verifique sus credenciales", Toast.LENGTH_SHORT).show()
                }
            }
    }

}
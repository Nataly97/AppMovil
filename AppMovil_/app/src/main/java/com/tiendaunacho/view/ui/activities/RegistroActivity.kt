package com.tiendaunacho.view.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.tiendaunacho.R
import java.security.AuthProvider

class RegistroActivity : AppCompatActivity() {

    lateinit var buttonregistro:Button
    private lateinit var firebaseAuth:FirebaseAuth

    //
    private lateinit var nombre:EditText
    private lateinit var dbReference:DatabaseReference
    private lateinit var database: FirebaseDatabase


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        firebaseAuth=Firebase.auth


        //
        database=FirebaseDatabase.getInstance()
        dbReference=database.reference.child("Usuario")


        buttonregistro=findViewById(R.id.BRegistrar)
        val correo=findViewById<EditText>(R.id.correologin)
        val contrasena=findViewById<EditText>(R.id.contrasenalog)
        nombre=findViewById(R.id.nombregistro)
        buttonregistro.setOnClickListener {
            crearcuenta(correo.text.toString(),contrasena.text.toString())
        }


        buttonregistro.setOnClickListener {
            crearcuenta(correo.text.toString(),contrasena.text.toString())
        }

    }
    private fun crearcuenta(correo:String,contrasena: String ) {
        val name:String=nombre.text.toString()
        firebaseAuth.createUserWithEmailAndPassword(correo, contrasena)
            .addOnCompleteListener(this){
            Task-> if(Task.isSuccessful){
                val user=firebaseAuth.currentUser
                val userdb=dbReference.child(user?.uid.toString())
                userdb.child("name").setValue(name)
                startActivity(Intent(this,HomeActivity::class.java))

            }
        }
    }

}
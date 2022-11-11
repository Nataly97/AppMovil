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
import com.google.firebase.ktx.Firebase
import com.tiendaunacho.R
import java.security.AuthProvider

class RegistroActivity : AppCompatActivity() {

      lateinit var buttonregistro:Button
      private lateinit var firebaseAuth:FirebaseAuth

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

       /* iNstancia Variable de logueo */

        firebaseAuth=Firebase.auth
        buttonregistro=findViewById(R.id.registro)
        val correo=findViewById<EditText>(R.id.correoregistro)
        val contrasena=findViewById<EditText>(R.id.contrasenaregistro)

        buttonregistro.setOnClickListener {
            crearcuenta(correo.text.toString(),contrasena.text.toString())
        }

    }

    private fun crearcuenta(correo:String,contrasena: String ) {
        firebaseAuth.createUserWithEmailAndPassword(correo,contrasena)
            .addOnCompleteListener(this){
             Task-> if(Task.isSuccessful){
                 Toast.makeText(baseContext,"Cuenta creada con exito",Toast.LENGTH_SHORT).show()
                 startActivity(Intent(this,HomeActivity::class.java))
            }else{
                Toast.makeText(baseContext,"Error, por favor revise la informacion ingresada",Toast.LENGTH_SHORT).show()

            }
        }
    }

}
package com.tiendaunacho.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.tiendaunacho.R

class LoginActivity : AppCompatActivity() {

    lateinit var InicioButton: Button
    lateinit var RegistroButton: Button
    lateinit var RecuperarButton: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        InicioButton=findViewById(R.id.Binicio)
        RegistroButton=findViewById(R.id.Bregistro)
        RecuperarButton=findViewById(R.id.Brecuperar)

        InicioButton.setOnClickListener {
            startActivity(Intent(this,HomeActivity::class.java ))
        }
        RegistroButton.setOnClickListener {
            startActivity(Intent(this, RegistroActivity::class.java))
        }
        RecuperarButton.setOnClickListener {
            startActivity(Intent(this, RecuperarActivity::class.java))
        }

    }
}
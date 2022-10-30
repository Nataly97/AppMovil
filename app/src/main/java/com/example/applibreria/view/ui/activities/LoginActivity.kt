package com.example.applibreria.view.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.applibreria.R

class LoginActivity : AppCompatActivity() {
    lateinit var iniciobutton:Button
    lateinit var registrobutton:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}
package com.tiendaunacho.view.ui.activities

import android.content.Intent
import android.content.IntentSender.SendIntentException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.tiendaunacho.R
import com.tiendaunacho.R.raw.*
import com.tiendaunacho.databinding.ActivityMainBinding
import kotlinx.coroutines.delay

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.animationView.setAnimation(animations)
        binding.animationView.playAnimation()

        handler = Handler(Looper.myLooper()!!)
        handler.postDelayed(
            {
                val intent=Intent(this,LoginActivity::class.java )
                startActivity(intent)
                finish()
            },  4000)

    }
}
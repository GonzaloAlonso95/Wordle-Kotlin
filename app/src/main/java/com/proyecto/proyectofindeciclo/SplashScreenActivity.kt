package com.proyecto.proyectofindeciclo

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.app.ActivityCompat

class SplashScreenActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT:Long = 1000 // Retraso de 1 segundo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler().postDelayed(Runnable { // This method will be executed once the timer is over
            startActivity(Intent(this, MainActivity::class.java))

            // Cerramos las activity
            //finish()
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),1)

            }
        }, SPLASH_TIME_OUT)
        return
    }
    override fun onStop() {
        super.onStop()
        this.finish()
    }
}
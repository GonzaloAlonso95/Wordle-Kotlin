package com.proyecto.proyectofindeciclo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import com.proyecto.proyectofindeciclo.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private val array_palabras = arrayOf("REINO", "SEGAR", "VISTA", "ZORRO", "CALOR", "CESTA", "POSTE", "PISTA", "RESTA", "TRETA")
    private var n_fila=0
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adivinar=array_palabras[(0..9).random()]
        Toast.makeText(this, adivinar, Toast.LENGTH_SHORT).show()
        binding.comprobar.setOnClickListener {
            comprobarLetras()
        }
    }

    fun comprobarLetras(){
        if (binding.palabra.text.toString().length==5){
            val palabra_comprobar=binding.palabra.text.toString().toUpperCase().toCharArray(0,5)
            val fila:TableRow = binding.tabla.getChildAt(n_fila) as TableRow
            println("Número de celdas en la fila: "+fila.childCount)
            for (i in 0 until fila.childCount){
                println(fila.getChildAt(i))
                val letra:TextView=fila.getChildAt(i) as TextView
                letra.text = palabra_comprobar[i].toString()
            }
            n_fila++
            binding.palabra.text.clear()
        }else{
            Toast.makeText(this, "La palabra tiene que ser de 5 letras", Toast.LENGTH_SHORT).show()
        }
    }

    fun pintarCeldas(){

    }
}